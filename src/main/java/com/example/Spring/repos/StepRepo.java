package com.example.Spring.repos;

import com.example.Spring.domain.Step;
import com.example.Spring.domain.User;
import org.springframework.data.repository.CrudRepository;
import java.time.LocalDate;
import java.util.List;

public interface StepRepo extends CrudRepository<Step, Long> {
    List<Step> findByAuthor(User user);
    List<Step> findByDataBetween(LocalDate dataFrom, LocalDate dataTo);
    List<Step> findAllByName(String name);
}
