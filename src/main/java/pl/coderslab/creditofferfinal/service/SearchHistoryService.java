package pl.coderslab.creditofferfinal.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.creditofferfinal.dto.ClientDTO;
import pl.coderslab.creditofferfinal.dto.SearchHistoryDTO;
import pl.coderslab.creditofferfinal.entity.Offer;
import pl.coderslab.creditofferfinal.entity.SearchHistory;
import pl.coderslab.creditofferfinal.exception.TypeOfLoanNotFoundException;
import pl.coderslab.creditofferfinal.mapper.ClientMapper;
import pl.coderslab.creditofferfinal.mapper.SearchHistoryMapper;
import pl.coderslab.creditofferfinal.repository.OfferRepository;
import pl.coderslab.creditofferfinal.repository.SearchHistoryRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchHistoryService {
    private final SearchHistoryRepository searchHistoryRepository;
    public final SearchHistoryMapper searchHistoryMapper;
    private final OfferRepository offerRepository;
    private final ClientMapper clientMapper;

    public List<SearchHistoryDTO> getAllSearchHistory(){
        List<SearchHistory> searchHistory = searchHistoryRepository.findAll();
        return searchHistoryMapper.toDtoList(searchHistory);
    }

    public SearchHistoryDTO getSearchHistoryById(Long id){
        Optional<SearchHistory> optionalSearchHistory = searchHistoryRepository.findById(id);
        if (optionalSearchHistory.isPresent()){
            SearchHistory searchHistory = optionalSearchHistory.get();
            return searchHistoryMapper.toDto(searchHistory);
        }
        throw new TypeOfLoanNotFoundException("SearchHistory kredytu o podanym ID nie istnieje");
    }

    public SearchHistoryDTO createSearchHistory(SearchHistoryDTO searchHistoryDTO){
        SearchHistory searchHistory = searchHistoryMapper.toEntity(searchHistoryDTO);
        SearchHistory createSearchHistory = searchHistoryRepository.save(searchHistory);
        return searchHistoryMapper.toDto(createSearchHistory);
    }

    public SearchHistoryDTO updateSearchHistory(Long id, SearchHistoryDTO searchHistoryDTO){
        Optional<SearchHistory> optionalSearchHistory = searchHistoryRepository.findById(id);
        if (optionalSearchHistory.isPresent()){
            SearchHistory searchHistory = optionalSearchHistory.get();
            searchHistory.setAmount(searchHistoryDTO.getAmount());
            SearchHistory updatedSearchHistory = searchHistoryRepository.save(searchHistory);
            return searchHistoryMapper.toDto(updatedSearchHistory);
        }
        throw new TypeOfLoanNotFoundException("SearchHistory o podanym ID nie istnieje");
    }

    public SearchHistoryDTO deleteSearchHistory(Long id){
        Optional<SearchHistory> optionalSearchHistory = searchHistoryRepository.findById(id);
        if (optionalSearchHistory.isPresent()){
            SearchHistory searchHistory = optionalSearchHistory.get();
            searchHistoryRepository.deleteById(id);
            return searchHistoryMapper.toDto(searchHistory);
        }
        throw new TypeOfLoanNotFoundException("SearchHistory o podanym ID nie istnieje");
    }

    public void filterAndSaveSearch(ClientDTO clientDTO, BigDecimal maxAmount, BigDecimal maxRrso, BigDecimal maxCommissionPercent, Integer maxPeriodInMonths) {
        List<Offer> matchingOffers = offerRepository.findAll();

        if (maxAmount != null) {
            List<Offer> matchingOffersByMaxAmount = offerRepository.findByMaximumAmountGreaterThan(maxAmount);
            matchingOffers.retainAll(matchingOffersByMaxAmount);
        }
        if (maxCommissionPercent != null) {
            List<Offer> matchingOffersByCommissionPercent = offerRepository.findByCommissionPercentLessThan(maxCommissionPercent);
            matchingOffers.retainAll(matchingOffersByCommissionPercent);
        }
        if (maxPeriodInMonths != null) {
            List<Offer> matchingOffersByPeriodInMonths = offerRepository.findByPeriodInMonthsGreaterThan(maxPeriodInMonths);
            matchingOffers.retainAll(matchingOffersByPeriodInMonths);
        }
        if (maxRrso != null) {
            List<Offer> matchingOffersByRrso = offerRepository.findByRRSOLessThan(maxRrso);
            matchingOffers.retainAll(matchingOffersByRrso);
        }
        if (matchingOffers.isEmpty()) {
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setAmount(maxAmount);
            searchHistory.setMaxRrso(maxRrso);
            searchHistory.setMaxCommissionPercent(maxCommissionPercent);
            searchHistory.setMaxPeriodInMonths(maxPeriodInMonths);
            searchHistory.setClient(clientMapper.toEntity(clientDTO));
            searchHistoryRepository.save(searchHistory);
        }
    }

    public List<Offer> matchingOffers(SearchHistory searchHistory) {
        List<Offer> allOffers = offerRepository.findAll();

        List<Offer> matchingOffers = allOffers.stream()
                .filter(offer -> searchHistory.getAmount() == null || offer.getMaximumAmount().compareTo(searchHistory.getAmount()) >= 0)
                .filter(offer -> searchHistory.getMaxRrso() == null || offer.getRRSO().compareTo(searchHistory.getMaxRrso()) <= 0)
                .filter(offer -> searchHistory.getMaxCommissionPercent() == null || offer.getCommissionPercent().compareTo(searchHistory.getMaxCommissionPercent()) <= 0)
                .filter(offer -> searchHistory.getMaxPeriodInMonths() == null || offer.getPeriodInMonths() >= searchHistory.getMaxPeriodInMonths())
                .collect(Collectors.toList());

        return matchingOffers;
    }
}
