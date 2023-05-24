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
        offer.setMaximumAmount(new BigDecimal("1000000"));
        offer.setRRSO(new BigDecimal("10.89"));
        offer.setCommissionPercent(new BigDecimal("5"));
        offer.setPeriodInMonths(360L);

        // Tworzenie przykładowego obiektu SearchHistory z filtrami
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setAmount(new BigDecimal("10000"));
        searchHistory.setMaxRrso(new BigDecimal("13"));
        searchHistory.setMaxCommissionPercent(new BigDecimal("6"));
        searchHistory.setMaxPeriodInMonths(360L);

        // Sprawdzanie, czy oferta pasuje do filtrów
        boolean isMatching = clientService.isOfferMatchingFilters(offer, searchHistory);

        assertTrue(isMatching);
    }
}
