package pl.coderslab.creditofferfinal.entity;

import lombok.*;

import javax.persistence.*;
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

    @Column(name = "name")
    private String name;

    @Column(name = "minimum_amount")
    private BigDecimal minimum_amount;

    @Column(name = "maximum_amount")
    private BigDecimal maximum_amount;

    @Column(name = "rrso")
    private BigDecimal rrso;

    @Column(name = "commission_percent")
    private BigDecimal commission_percent;

    @Column(name = "period_in_months")
    private Integer period_in_months;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private TypeOfLoan typeOfLoan;

}
