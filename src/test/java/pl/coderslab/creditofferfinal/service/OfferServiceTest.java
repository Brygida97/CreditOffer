package pl.coderslab.creditofferfinal.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.coderslab.creditofferfinal.entity.Bank;
import pl.coderslab.creditofferfinal.entity.Offer;
import pl.coderslab.creditofferfinal.entity.TypeOfLoan;
import pl.coderslab.creditofferfinal.repository.OfferRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OfferServiceTest {

    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private OfferService offerService;

    private List<Offer> offers;

    @BeforeEach
    void setup() {
        Bank newBank1 = new Bank(1L, "Bank1");
        Bank newBank2 = new Bank(2L, "Bank2");

        TypeOfLoan newType1 = new TypeOfLoan(1L, "rodzaj kredytu 1");
        TypeOfLoan newType2 = new TypeOfLoan(2L, "rodzaj kredytu 2");

        offers = new ArrayList<>();

        offers.add(new Offer(1L, "Oferta1", new BigDecimal(1000.00), new BigDecimal(1000.00), new BigDecimal(13.99), new BigDecimal(3.00), 120, "https://bank1.com/oferta1", newBank1, newType1));
        offers.add(new Offer(2L, "Oferta1", new BigDecimal(1000.00), new BigDecimal(1000.00), new BigDecimal(12.99), new BigDecimal(4.00), 120, "https://bank1.com/oferta1", newBank2, newType1));
        offers.add(new Offer(3L, "Oferta1", new BigDecimal(1000.00), new BigDecimal(1000.00), new BigDecimal(11.99), new BigDecimal(5.00), 120, "https://bank1.com/oferta1", newBank1, newType2));
        offers.add(new Offer(4L, "Oferta1", new BigDecimal(1000.00), new BigDecimal(1000.00), new BigDecimal(10.99), new BigDecimal(6.00), 120, "https://bank1.com/oferta1", newBank2, newType1));
        offers.add(new Offer(5L, "Oferta1", new BigDecimal(1000.00), new BigDecimal(1000.00), new BigDecimal(9.99), new BigDecimal(7.00), 120, "https://bank1.com/oferta1", newBank1, newType2));

    }

    @Test
    void getOffersWithLowestRRSO() {

        when(offerRepository.findAll()).thenReturn(offers);

        List<Offer> result = offerService.getOffersWithLowestRRSO(3);

        List<Long> expectedOfferIds = Arrays.asList(5L, 4L, 3L);
        List<Long> resultOfferIds = result.stream()
                .map(Offer::getId)
                .collect(Collectors.toList());

        assertEquals(expectedOfferIds, resultOfferIds);
    }

    @Test
    void getOffersWithLowestCommission() {
        when(offerRepository.findAll()).thenReturn(offers);

        List<Offer> result = offerService.getOffersWithLowestCommission(3);

        List<Long> expectedOfferIds = Arrays.asList(1L, 2L, 3L);
        List<Long> resultOfferIds = result.stream()
                .map(Offer::getId)
                .collect(Collectors.toList());

        assertEquals(expectedOfferIds, resultOfferIds);
    }

    @Test
    void getMatchingOffersWithLowestRRSOAndCommission() {
        when(offerRepository.findAll()).thenReturn(offers);

        List<Offer> result = offerService.getMatchingOffersWithLowestRRSOAndCommission(3);

        List<Long> expectedOfferIds = Arrays.asList(3L);
        List<Long> resultOfferIds = result.stream()
                .map(Offer::getId)
                .collect(Collectors.toList());

        assertEquals(expectedOfferIds, resultOfferIds);
    }
}