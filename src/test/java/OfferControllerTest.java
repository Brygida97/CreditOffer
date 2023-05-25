//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.server.ResponseStatusException;
//import pl.coderslab.creditofferfinal.controller.OfferController;
//import pl.coderslab.creditofferfinal.entity.Offer;
//import pl.coderslab.creditofferfinal.exception.OfferNotFoundException;
//import pl.coderslab.creditofferfinal.service.ClientService;
//import pl.coderslab.creditofferfinal.service.OfferService;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//
//public class OfferControllerTest {
//    private OfferController offerController;
//
//    @Mock
//    private OfferService offerService;
//
//    @Mock
//    private ClientService clientService;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        offerController = new OfferController();
//    }
//
//
//    @Test
//    public void testGetMatchingOffers_ReturnsMatchingOffers() throws OfferNotFoundException {
//        // Arrange
//        List<Offer> expectedOffers = Arrays.asList(
//                new Offer(1,"Oferta testowa1",1000.00,10000.00,11.90,4.00,100,"https://www.xxx.pl",1,2),
//                new Offer(2,"Oferta testowa2",1000.00,10000.00,12.90,3.00,120,"https://www.xxx.pl",1,2),
//                new Offer(3,"Oferta testowa3",1000.00,10000.00,13.90,2.00,140,"https://www.xxx.pl",1,2),
//                new Offer(4,"Oferta testowa4",1000.00,10000.00,12.90,1.00,150,"https://www.xxx.pl",1,2)
//        );
//        when(offerService.getMatchingOffersWithLowestRRSOAndCommission(3)).thenReturn(expectedOffers);
//
//        // Act
//        List<Offer> actualOffers = offerController.getMatchingOffers();
//
//        // Assert
//        Assertions.assertEquals(expectedOffers, actualOffers);
//        verify(offerService, times(1)).getMatchingOffersWithLowestRRSOAndCommission(3);
//    }
//
//    @Test
//    public void testGetMatchingOffers_ThrowsOfferNotFoundException() throws OfferNotFoundException {
//        // Arrange
//        when(offerService.getMatchingOffersWithLowestRRSOAndCommission(3)).thenThrow(new OfferNotFoundException("Offers not found"));
//
//        // Act & Assert
//        Assertions.assertThrows(ResponseStatusException.class, () -> {
//            offerController.getMatchingOffers();
//        });
//        verify(offerService, times(1)).getMatchingOffersWithLowestRRSOAndCommission(3);
//    }
//}
