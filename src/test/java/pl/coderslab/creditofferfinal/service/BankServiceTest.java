package pl.coderslab.creditofferfinal.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.coderslab.creditofferfinal.entity.Bank;
import pl.coderslab.creditofferfinal.entity.Offer;
import pl.coderslab.creditofferfinal.entity.TypeOfLoan;
import pl.coderslab.creditofferfinal.repository.BankRepository;
import pl.coderslab.creditofferfinal.repository.OfferRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankServiceTest {

    @Mock
    private BankRepository bankRepository;
    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private BankService bankService;


    @Test
    void getBankOfferCounts() {

        Bank newBank1 = new Bank(1L, "Bank1");
        Bank newBank2 = new Bank(2L, "Bank2");

        TypeOfLoan newType1 = new TypeOfLoan(1L, "rodzaj kredytu 1");
        TypeOfLoan newType2 = new TypeOfLoan(2L, "rodzaj kredytu 2");

        Offer newOffer1 = new Offer(1L, "Oferta1", new BigDecimal(1000.00), new BigDecimal(1000.00), new BigDecimal(12.99), new BigDecimal(21.00), 120, "https://bank1.com/oferta1", newBank1, newType1);
        Offer newOffer2 = new Offer(2L, "Oferta1", new BigDecimal(1000.00), new BigDecimal(1000.00), new BigDecimal(12.99), new BigDecimal(21.00), 120, "https://bank1.com/oferta1", newBank2, newType1);
        Offer newOffer3 = new Offer(3L, "Oferta1", new BigDecimal(1000.00), new BigDecimal(1000.00), new BigDecimal(12.99), new BigDecimal(21.00), 120, "https://bank1.com/oferta1", newBank1, newType2);
        Offer newOffer4 = new Offer(4L, "Oferta1", new BigDecimal(1000.00), new BigDecimal(1000.00), new BigDecimal(12.99), new BigDecimal(21.00), 120, "https://bank1.com/oferta1", newBank2, newType1);
        Offer newOffer5 = new Offer(5L, "Oferta1", new BigDecimal(1000.00), new BigDecimal(1000.00), new BigDecimal(12.99), new BigDecimal(21.00), 120, "https://bank1.com/oferta1", newBank1, newType2);
        Offer newOffer6 = new Offer(6L, "Oferta1", new BigDecimal(1000.00), new BigDecimal(1000.00), new BigDecimal(12.99), new BigDecimal(21.00), 120, "https://bank1.com/oferta1", newBank1, newType1);

        when(bankRepository.findAll()).thenReturn(Arrays.asList(newBank1, newBank2));
        when(offerRepository.countByBankId(1L)).thenReturn(4L);
        when(offerRepository.countByBankId(2L)).thenReturn(2L);

        List<Map<String, Object>> result = bankService.getBankOfferCounts();

        assertEquals(2, result.size());

        Map<String, Object> expectedBank1 = new HashMap<>();
        expectedBank1.put("bankId", 1L);
        expectedBank1.put("bankName", "Bank1");
        expectedBank1.put("offerCount", 4L);

        Map<String, Object> expectedBank2 = new HashMap<>();
        expectedBank2.put("bankId", 2L);
        expectedBank2.put("bankName", "Bank2");
        expectedBank2.put("offerCount", 2L);

        assertEquals(expectedBank1, result.get(0));
        assertEquals(expectedBank2, result.get(1));

        verify(bankRepository, times(1)).findAll();
        verify(offerRepository, times(1)).countByBankId(1L);
        verify(offerRepository, times(1)).countByBankId(2L);
    }
}