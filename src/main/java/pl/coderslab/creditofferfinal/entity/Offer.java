package pl.coderslab.creditofferfinal.entity;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "offers")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @DecimalMin(value = "0")
    @Column(name = "minimum_amount")
    private BigDecimal minimumAmount;

    @DecimalMin(value = "0")
    @Column(name = "maximum_amount")
    private BigDecimal maximumAmount;

    @DecimalMin(value = "0")
    @Column(name = "rrso")
    private BigDecimal RRSO;

    @DecimalMin(value = "0")
    @Column(name = "commission_percent")
    private BigDecimal commissionPercent;

    @Min(value = 0)
    @Column(name = "period_in_months")
    private Integer periodInMonths;

    @NotBlank
    @URL
    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private TypeOfLoan typeOfLoan;

}
