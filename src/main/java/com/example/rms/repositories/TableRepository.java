package com.example.rms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.rms.entities.Table;

@Repository
public interface TableRepository extends CrudRepository<Table, Long>{
    @Query("SELECT t FROM Table t WHERE t.available = false")
    List<Table> findByAvailableFalse();
    List<Table> findBySeatNum(int seatNum);
}
