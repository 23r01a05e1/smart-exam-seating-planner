package com.examplanner.backend.controller;

import com.examplanner.backend.model.SeatAssignment;
import com.examplanner.backend.service.SeatingAlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/seating")
@CrossOrigin(origins = "http://localhost:3000")
public class SeatingController {

    @Autowired
    private SeatingAlgorithmService seatingAlgorithmService;

    @PostMapping("/generate")
    public List<SeatAssignment> generatePlan() {
        return seatingAlgorithmService.generateSeatingPlan();
    }

    @GetMapping
    public List<SeatAssignment> getPlan() {
        return seatingAlgorithmService.getSeatingPlan();
    }
}