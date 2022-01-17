package com.example.Spring.repos;

import com.example.Spring.domain.ListCommand;
import org.springframework.data.repository.CrudRepository;

public interface ListCommandRepo extends CrudRepository<ListCommand, Long> {
}
