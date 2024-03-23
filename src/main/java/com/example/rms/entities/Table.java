package com.example.rms.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "Tables")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String seatNum;
    private boolean available;

    @OneToOne
    @JoinTable(name = "user_table", joinColumns = @JoinColumn(name = "table_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    @OneToMany(mappedBy = "table")
    private Set<Order> orders;
}
