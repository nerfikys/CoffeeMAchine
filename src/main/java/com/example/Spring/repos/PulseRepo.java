package com.example.Spring.repos;

import com.example.Spring.domain.Pulse;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PulseRepo extends CrudRepository<Pulse, Long> {

    List<Pulse> findByName(String name);

}
