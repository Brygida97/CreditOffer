package pl.coderslab.creditofferfinal.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.creditofferfinal.dto.ClientDTO;
import pl.coderslab.creditofferfinal.dto.SearchHistoryDTO;
import pl.coderslab.creditofferfinal.dto.TypeOfLoanDTO;
import pl.coderslab.creditofferfinal.entity.Client;
import pl.coderslab.creditofferfinal.entity.Offer;
import pl.coderslab.creditofferfinal.entity.SearchHistory;
import pl.coderslab.creditofferfinal.exception.SearchHistoryNotFoundException;
import pl.coderslab.creditofferfinal.mapper.ClientMapper;
import pl.coderslab.creditofferfinal.service.ClientService;
import pl.coderslab.creditofferfinal.service.SearchHistoryService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/searchHistory")
@AllArgsConstructor
public class SearchController {

    private final SearchHistoryService searchHistoryService;
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping
    public List<SearchHistoryDTO> getAllSearchHistory() {
        List<SearchHistoryDTO> searchHistory = searchHistoryService.getAllSearchHistory();
        return searchHistory;
    }

    @GetMapping("/{id}")
    public SearchHistoryDTO getSearchHistoryById(@PathVariable Long id) {
        try {
            return searchHistoryService.getSearchHistoryById(id);
        } catch (SearchHistoryNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping
    public SearchHistoryDTO createSearchHistory(@RequestBody SearchHistoryDTO searchHistoryDTO) {
        return searchHistoryService.createSearchHistory(searchHistoryDTO);
    }

    @PutMapping("/{id}")
    public SearchHistoryDTO updateSearchHistory(@PathVariable Long id, @RequestBody SearchHistoryDTO searchHistoryDTO) {
        try {
            return searchHistoryService.updateSearchHistory(id, searchHistoryDTO);
        } catch (SearchHistoryNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSearchHistory(@PathVariable Long id) {
        try {
            searchHistoryService.deleteSearchHistory(id);
            return ResponseEntity.ok(String.format("SearchHistory o ID %s został usunięty", id));
        } catch (SearchHistoryNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping("/offers/filter")
    public ResponseEntity<?> filterOffers(@RequestBody SearchHistory searchHistory) {
        BigDecimal maxAmount = searchHistory.getAmount();
        BigDecimal maxRrso = searchHistory.getMaxRrso();
        BigDecimal maxCommissionPercent = searchHistory.getMaxCommissionPercent();
        Integer maxPeriodInMonths = searchHistory.getMaxPeriodInMonths();

        Client client = searchHistory.getClient();
        ClientDTO clientDTO = clientMapper.toDto(client);

        List<Offer> matchingOffers = searchHistoryService.matchingOffers(searchHistory);

        if (matchingOffers.isEmpty()) {
            searchHistoryService.filterAndSaveSearch(clientDTO, maxAmount, maxRrso, maxCommissionPercent, maxPeriodInMonths);
            String errorMessage = "Niestety nie odnajdujemy oferty spełniającej Twoich oczekiwań. W przypadku dodania takiej oferty skontaktujemy się z Tobą mailowo.";
            return ResponseEntity.ok(errorMessage);
        } else {
            return ResponseEntity.ok(matchingOffers);
        }
    }
}



