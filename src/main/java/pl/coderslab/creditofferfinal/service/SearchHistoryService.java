package pl.coderslab.creditofferfinal.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.creditofferfinal.dto.SearchHistoryDTO;
import pl.coderslab.creditofferfinal.entity.SearchHistory;
import pl.coderslab.creditofferfinal.exception.TypeOfLoanNotFoundException;
import pl.coderslab.creditofferfinal.mapper.SearchHistoryMapper;
import pl.coderslab.creditofferfinal.repository.SearchHistoryRepository;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class SearchHistoryService {
    private final SearchHistoryRepository searchHistoryRepository;
    private final SearchHistoryMapper searchHistoryMapper;

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

}
