package com.examplanner.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "halls")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int capacity;

    @Column(name = "`rows`")
    private int rows;

    @Column(name = "`columns`")
    private int columns;
}
