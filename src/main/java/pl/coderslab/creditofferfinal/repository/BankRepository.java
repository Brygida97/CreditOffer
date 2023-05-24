package pl.coderslab.creditofferfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.creditofferfinal.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

}
