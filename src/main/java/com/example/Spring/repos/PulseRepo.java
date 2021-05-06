package com.example.Spring.repos;

import com.example.Spring.domain.Pulse;
import com.example.Spring.domain.User;
import org.springframework.data.repository.CrudRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface PulseRepo extends CrudRepository<Pulse, Long> {
    List<Pulse> findByAuthor(User user);
    List<Pulse> findByDataBetween(LocalDateTime dataFrom, LocalDateTime dataTo);
    List<Pulse> findAllByName(String name);
}
