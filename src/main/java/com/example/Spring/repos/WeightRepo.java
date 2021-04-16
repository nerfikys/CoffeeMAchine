package com.example.Spring.repos;

import com.example.Spring.domain.Weight;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface WeightRepo extends CrudRepository<Weight, Long> {
    List<Weight> findByName(String name);
}
