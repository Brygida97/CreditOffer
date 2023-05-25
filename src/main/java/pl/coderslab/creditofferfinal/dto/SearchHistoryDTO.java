package pl.coderslab.creditofferfinal.dto;

import lombok.*;
import pl.coderslab.creditofferfinal.entity.Client;
import pl.coderslab.creditofferfinal.entity.Offer;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchHistoryDTO {

    private Long id;
    private BigDecimal amount;
    private BigDecimal maxRrso;
    private BigDecimal maxCommissionPercent;
    private Integer maxPeriodInMonths;
    private Client client;
    private Offer matchingOffer;
}

