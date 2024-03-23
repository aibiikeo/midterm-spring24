package com.example.rms.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.rms.entities.Table;

public interface TableRepository extends CrudRepository<Table, Long>{
    
}
