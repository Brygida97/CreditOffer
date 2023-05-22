package pl.coderslab.creditofferfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.creditofferfinal.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
