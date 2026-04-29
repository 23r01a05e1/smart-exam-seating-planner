package com.examplanner.backend.service;

import com.examplanner.backend.model.Hall;
import com.examplanner.backend.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HallService {

    @Autowired
    private HallRepository hallRepository;

    public Hall addHall(Hall hall) {
        return hallRepository.save(hall);
    }

    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    public void deleteHall(Long id) {
        hallRepository.deleteById(id);
    }
}