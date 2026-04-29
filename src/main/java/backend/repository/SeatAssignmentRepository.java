package com.examplanner.backend.repository;

import com.examplanner.backend.model.SeatAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatAssignmentRepository extends JpaRepository<SeatAssignment, Long> {
}