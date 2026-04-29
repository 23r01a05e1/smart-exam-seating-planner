package com.examplanner.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "seat_assignments")
public class SeatAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rowNum;

    private int colNum;

    private String hallName;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}