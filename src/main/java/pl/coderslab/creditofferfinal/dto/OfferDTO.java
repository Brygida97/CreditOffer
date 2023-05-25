package pl.coderslab.creditofferfinal.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OfferDTO {
    private Long id;
    private String name;
    private BigDecimal minimumAmount;
    private BigDecimal maximumAmount;
    private BigDecimal RRSO;
    private BigDecimal commissionPercent;
    private Integer periodInMonths;
    private String url;
    private BankDTO bank;
    private TypeOfLoanDTO typeOfLoan;

}
