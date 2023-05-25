package pl.coderslab.creditofferfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.creditofferfinal.entity.Client;
import pl.coderslab.creditofferfinal.entity.Offer;
import pl.coderslab.creditofferfinal.entity.SearchHistory;

import java.util.List;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
}
