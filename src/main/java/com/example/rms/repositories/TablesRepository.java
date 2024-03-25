package com.example.rms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.rms.entities.Tables;

@Repository
public interface TablesRepository extends CrudRepository<Tables, Long>{
    @Query("SELECT t FROM Tables t WHERE t.available = false")
    List<Tables> findByAvailableFalse();
    List<Tables> findBySeatNum(int seatNum);
}
