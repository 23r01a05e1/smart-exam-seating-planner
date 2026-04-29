package com.examplanner.backend.controller;

import com.examplanner.backend.model.Hall;
import com.examplanner.backend.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/halls")
@CrossOrigin(origins = "http://localhost:3000")
public class HallController {

    @Autowired
    private HallService hallService;

    @PostMapping
    public Hall addHall(@RequestBody Hall hall) {
        return hallService.addHall(hall);
    }

    @GetMapping
    public List<Hall> getAllHalls() {
        return hallService.getAllHalls();
    }

    @DeleteMapping("/{id}")
    public void deleteHall(@PathVariable Long id) {
        hallService.deleteHall(id);
    }
}