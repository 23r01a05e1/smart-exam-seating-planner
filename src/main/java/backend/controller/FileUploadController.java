package com.examplanner.backend.controller;

import com.examplanner.backend.model.Faculty;
import com.examplanner.backend.model.Hall;
import com.examplanner.backend.model.Student;
import com.examplanner.backend.repository.FacultyRepository;
import com.examplanner.backend.repository.HallRepository;
import com.examplanner.backend.repository.SeatAssignmentRepository;
import com.examplanner.backend.repository.StudentRepository;
import com.examplanner.backend.service.ExcelReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "http://localhost:3000")
public class FileUploadController {

    @Autowired
    private ExcelReaderService excelReaderService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private SeatAssignmentRepository seatAssignmentRepository;

    @PostMapping("/students")
    public String uploadStudents(@RequestParam("file") MultipartFile file) {
        try {
            List<Student> students = excelReaderService.readStudents(file);
            seatAssignmentRepository.deleteAll();
            studentRepository.deleteAll();
            studentRepository.saveAll(students);
            return "Successfully uploaded " + students.size() + " students!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/faculty")
    public String uploadFaculty(@RequestParam("file") MultipartFile file) {
        try {
            List<Faculty> faculty = excelReaderService.readFaculty(file);
            facultyRepository.deleteAll();
            facultyRepository.saveAll(faculty);
            return "Successfully uploaded " + faculty.size() + " faculty members!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/halls")
    public String uploadHalls(@RequestParam("file") MultipartFile file) {
        try {
            List<Hall> halls = excelReaderService.readHalls(file);
            seatAssignmentRepository.deleteAll();
            hallRepository.deleteAll();
            hallRepository.saveAll(halls);
            return "Successfully uploaded " + halls.size() + " halls!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}