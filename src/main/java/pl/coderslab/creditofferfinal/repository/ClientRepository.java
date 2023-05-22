package pl.coderslab.creditofferfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.creditofferfinal.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
