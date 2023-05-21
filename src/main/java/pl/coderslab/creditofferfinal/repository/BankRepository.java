package pl.coderslab.creditofferfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.creditofferfinal.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

}
