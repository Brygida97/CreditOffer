package pl.coderslab.creditofferfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.creditofferfinal.entity.Offer;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByMaximumAmountGreaterThan(BigDecimal maximumAmount);
    List<Offer> findByRRSOLessThan(BigDecimal rrso);
    List<Offer> findByCommissionPercentLessThan(BigDecimal commissionPercent);
    List<Offer> findByPeriodInMonthsGreaterThan(Long periodInMonths);

    //    List<Offer> findByMinimumAmountIsLessThan(BigDecimal minimumAmount);

}