package pl.coderslab.creditofferfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.creditofferfinal.entity.TypeOfLoan;

import java.util.Optional;

@Repository
public interface TypeOfLoanRepository extends JpaRepository<TypeOfLoan, Long> {
}
