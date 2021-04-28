package com.example.Spring.repos;

import com.example.Spring.domain.User;
import com.example.Spring.domain.Weight;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface WeightRepo extends CrudRepository<Weight, Long> {
    List<Weight> findByAuthor(User user);
    List<Weight> findByDataBetween(LocalDateTime dataFrom, LocalDateTime dataTo);
    List<Weight> findAllByName(String name);
}
