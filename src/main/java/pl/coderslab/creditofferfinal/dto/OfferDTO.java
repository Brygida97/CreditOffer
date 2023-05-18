package pl.coderslab.creditofferfinal.dto;

import lombok.*;
import pl.coderslab.creditofferfinal.entity.TypeOfLoan;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OfferDTO {
    private Long id;
    private String name;
    private BigDecimal minimum_amount;
    private BigDecimal maximum_amount;
    private BigDecimal rrso;
    private BigDecimal commission_percent;
    private Integer period_in_months;
    private String url;
    private BankDTO bank;
    private TypeOfLoanDTO typeOfLoan;

}
