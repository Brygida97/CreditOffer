package pl.coderslab.creditofferfinal.entity;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
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

    @NotBlank
    @Column(name = "minimum_amount")
    private BigDecimal minimumAmount;

    @NotBlank
    @Column(name = "maximum_amount")
    private BigDecimal maximumAmount;

    @NotBlank
    @Column(name = "rrso")
    private BigDecimal RRSO;

    @NotBlank
    @Column(name = "commission_percent")
    private BigDecimal commissionPercent;

    @NotBlank
    @Column(name = "period_in_months")
    private Integer periodInMonths;

    @NotBlank
    @URL
    @Column(name = "url")
    private String url;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private TypeOfLoan typeOfLoan;

}
