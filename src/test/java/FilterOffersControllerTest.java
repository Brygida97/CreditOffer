//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import pl.coderslab.creditofferfinal.entity.Client;
//import pl.coderslab.creditofferfinal.entity.Offer;
//import pl.coderslab.creditofferfinal.entity.SearchHistory;
//import pl.coderslab.creditofferfinal.mapper.ClientMapper;
//import pl.coderslab.creditofferfinal.mapper.OfferMapper;
//import pl.coderslab.creditofferfinal.repository.ClientRepository;
//import pl.coderslab.creditofferfinal.repository.OfferRepository;
//import pl.coderslab.creditofferfinal.repository.SearchHistoryRepository;
//import pl.coderslab.creditofferfinal.service.ClientService;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.when;
//
//public class CreditOfferControllerTest {
//
//    private CreditOfferController creditOfferController;
//
//    @Mock
//    private ClientRepository clientRepository;
//
//    @Mock
//    private ClientMapper clientMapper;
//
//    @Mock
//    private OfferRepository offerRepository;
//
//    @Mock
//    private SearchHistoryRepository searchHistoryRepository;
//
//    @Mock
//    private ClientService clientService;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        creditOfferController = new CreditOfferController(clientService);
//    }
//
//    @Test
//    public void testFilterOffers_NoMatchingOffers_ReturnsNoContent() {
//        // Mockowanie zwracanych wartości i zachowań
//        SearchHistory searchHistory = new SearchHistory();
//        Client client = new Client();
//        when(searchHistory.getClient()).thenReturn(client);
//        when(clientMapper.toDto(client)).thenReturn(new ClientDTO());
//        when(clientService.matchingOffers(searchHistory)).thenReturn(new ArrayList<>());
//
//        // Wywołanie metody testowanej
//        ResponseEntity<?> responseEntity = creditOfferController.filterOffers(searchHistory);
//
//        // Sprawdzenie oczekiwanych wyników
//        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
//        assertTrue(responseEntity.getBody() == null);
//    }
//
//    @Test
//    public void testFilterOffers_MatchingOffers_ReturnsMatchingOffers() {
//        // Mockowanie zwracanych wartości i zachowań
//        SearchHistory searchHistory = new SearchHistory();
//        Client client = new Client();
//        when(searchHistory.getClient()).thenReturn(client);
//        when(clientMapper.toDto(client)).thenReturn(new ClientDTO());
//
//        List<Offer> matchingOffers = new ArrayList<>();
//        matchingOffers.add(new Offer());
//        when(clientService.matchingOffers(searchHistory)).thenReturn(matchingOffers);
//
//        // Wywołanie metody testowanej
//        ResponseEntity<?> responseEntity = creditOfferController.filterOffers(searchHistory);
//
//        // Sprawdzenie oczekiwanych wyników
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertTrue(responseEntity.getBody() == matchingOffers);
//    }
//}
