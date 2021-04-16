package com.example.Spring.repos;

import com.example.Spring.domain.Step;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface StepRepo extends CrudRepository<Step, Long> {
    List<Step> findByName(String name);
}
