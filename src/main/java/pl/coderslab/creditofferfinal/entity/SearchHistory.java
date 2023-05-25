package pl.coderslab.creditofferfinal.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "searchHistory")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "max_rrso")
    private BigDecimal maxRrso;

    @Column(name = "max_commission_percent")
    private BigDecimal maxCommissionPercent;

    @Column(name = "max_period_in_months")
    private Integer maxPeriodInMonths;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    private Offer matchingOffer;
}
