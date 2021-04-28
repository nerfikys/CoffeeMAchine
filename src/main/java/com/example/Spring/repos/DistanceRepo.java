package com.example.Spring.repos;

import com.example.Spring.domain.Distance;
import com.example.Spring.domain.User;
import org.springframework.data.repository.CrudRepository;
import java.time.LocalDate;
import java.util.List;

public interface DistanceRepo extends CrudRepository<Distance, Long> {
    List<Distance> findByAuthor(User user);
    List<Distance> findByDataBetween(LocalDate dataFrom, LocalDate dataTo);
    List<Distance> findAllByName(String name);
}
