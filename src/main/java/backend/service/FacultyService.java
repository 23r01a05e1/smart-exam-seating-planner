package com.examplanner.backend.service;

import com.examplanner.backend.model.Faculty;
import com.examplanner.backend.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
}