package com.example.rms.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.rms.entities.Menu;

public interface MenuRepository extends CrudRepository<Menu, Long>{
    
}
