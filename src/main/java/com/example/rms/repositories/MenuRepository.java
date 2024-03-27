package com.example.rms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.rms.entities.Menu;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long>{
    List<Menu> findAll();
    List<Menu> findByName(String name);
    List<Menu> findByCategory(String category);
    List<Menu> findByPrice(String price);
    @Query("SELECT m.id, m.name, m.description, m.price, m.category FROM Menu m")
    List<Object[]> findAllMenuAttributes();
}
