package com.example.Spring.repos;

import com.example.Spring.domain.Distance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DistanceRepo extends CrudRepository<Distance, Long> {
    List<Distance> findByName(String name);
}
