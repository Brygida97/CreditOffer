package pl.coderslab.creditofferfinal.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.creditofferfinal.dto.ClientDTO;
import pl.coderslab.creditofferfinal.entity.Client;
import pl.coderslab.creditofferfinal.entity.Offer;
import pl.coderslab.creditofferfinal.entity.SearchHistory;
import pl.coderslab.creditofferfinal.mapper.ClientMapper;
import pl.coderslab.creditofferfinal.service.ClientService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SearchController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping("/offers/filter")
    public ResponseEntity<?> filterOffers(@RequestBody SearchHistory searchHistory) {
        BigDecimal maxAmount = searchHistory.getAmount();
        BigDecimal maxRrso = searchHistory.getMaxRrso();
        BigDecimal maxCommissionPercent = searchHistory.getMaxCommissionPercent();
        Integer maxPeriodInMonths = searchHistory.getMaxPeriodInMonths();

        Client client = searchHistory.getClient();
        ClientDTO clientDTO = clientMapper.toDto(client);
        clientService.filterAndSaveSearch(clientDTO, maxAmount, maxRrso, maxCommissionPercent, maxPeriodInMonths);

        List<Offer> matchingOffers = clientService.matchingOffers(searchHistory);

        if (matchingOffers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(matchingOffers);
        }
    }

}


