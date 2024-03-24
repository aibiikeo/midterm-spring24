package com.example.rms.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.rms.entities.Table;

public interface TableRepository extends CrudRepository<Table, Long>{
    List<Table> findByAvailableFalse();
}
