package pl.coderslab.creditofferfinal.mapper;

import org.springframework.stereotype.Component;
import pl.coderslab.creditofferfinal.dto.SearchHistoryDTO;
import pl.coderslab.creditofferfinal.entity.SearchHistory;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchHistoryMapper {
    public SearchHistoryDTO toDto(pl.coderslab.creditofferfinal.entity.SearchHistory searchHistory) {
        SearchHistoryDTO dto = new SearchHistoryDTO();
        dto.setId(searchHistory.getId());
        dto.setAmount(searchHistory.getAmount());
        dto.setMaxRrso(searchHistory.getMaxRrso());
        dto.setMaxCommissionPercent(searchHistory.getMaxCommissionPercent());
        dto.setMaxPeriodInMonths(searchHistory.getMaxPeriodInMonths());
        dto.setMatchingOffer(searchHistory.getMatchingOffer());
        return dto;
    }

    public SearchHistory toEntity(SearchHistoryDTO dto) {
        pl.coderslab.creditofferfinal.entity.SearchHistory searchHistory = new pl.coderslab.creditofferfinal.entity.SearchHistory();
        searchHistory.setId(dto.getId());
        searchHistory.setAmount(dto.getAmount());
        searchHistory.setMaxRrso(dto.getMaxRrso());
        searchHistory.setMaxCommissionPercent(dto.getMaxCommissionPercent());
        searchHistory.setMaxPeriodInMonths(dto.getMaxPeriodInMonths());
        searchHistory.setMatchingOffer(dto.getMatchingOffer());
        return searchHistory;
    }

    public List<SearchHistoryDTO> toDtoList(List<SearchHistory> searchHistory) {
        return searchHistory.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
