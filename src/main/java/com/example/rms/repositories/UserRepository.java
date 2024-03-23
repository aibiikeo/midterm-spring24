package com.example.rms.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.rms.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
    
}
