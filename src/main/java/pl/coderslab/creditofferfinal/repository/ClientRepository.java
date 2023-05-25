package pl.coderslab.creditofferfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.creditofferfinal.entity.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
//    @Query("SELECT c FROM Client c LEFT JOIN c.searchHistory sh WHERE sh.id IS NOT NULL AND c.email = :criteria")
//    List<Client> findClientsWithSearches(@Param("criteria") String criteria);

}
