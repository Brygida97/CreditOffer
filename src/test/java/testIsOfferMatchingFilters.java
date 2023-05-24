import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.coderslab.creditofferfinal.entity.Offer;
import pl.coderslab.creditofferfinal.entity.SearchHistory;
import pl.coderslab.creditofferfinal.mapper.ClientMapper;
import pl.coderslab.creditofferfinal.repository.ClientRepository;
import pl.coderslab.creditofferfinal.repository.OfferRepository;
import pl.coderslab.creditofferfinal.repository.SearchHistoryRepository;
import pl.coderslab.creditofferfinal.service.ClientService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class testIsOfferMatchingFilters {
    private ClientService clientService;
    private ClientRepository clientRepository;
    private ClientMapper clientMapper;
    private OfferRepository offerRepository;
    private SearchHistoryRepository searchHistoryRepository;

    @BeforeEach
    public void setup() {
        clientRepository = Mockito.mock(ClientRepository.class);
        clientMapper = Mockito.mock(ClientMapper.class);
        offerRepository = Mockito.mock(OfferRepository.class);
        searchHistoryRepository = Mockito.mock(SearchHistoryRepository.class);

        clientService = new ClientService(clientRepository, clientMapper, offerRepository, searchHistoryRepository);
    }

    @Test
    public void testIsOfferMatchingFilters() {
        // Tworzenie przykładowej oferty
        Offer offer = new Offer();
        offer.setMaximumAmount(new BigDecimal("250000"));
        offer.setRRSO(new BigDecimal("12"));
        offer.setCommissionPercent(new BigDecimal("2"));
        offer.setPeriodInMonths(120L);

        // Tworzenie przykładowego obiektu SearchHistory z filtrami
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setAmount(new BigDecimal("100000"));
        searchHistory.setMaxRrso(new BigDecimal("15"));
        searchHistory.setMaxCommissionPercent(new BigDecimal("2"));
        searchHistory.setMaxPeriodInMonths(100L);

        // Sprawdzanie, czy oferta pasuje do filtrów
        boolean isMatching = clientService.isOfferMatchingFilters(offer, searchHistory);

        assertTrue(isMatching);
    }
}