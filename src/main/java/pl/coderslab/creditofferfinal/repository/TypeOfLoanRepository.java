package pl.coderslab.creditofferfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.creditofferfinal.entity.TypeOfLoan;

import java.util.Optional;

public interface TypeOfLoanRepository extends JpaRepository<TypeOfLoan, Long> {
}
