package com.examplanner.backend.service;

import com.examplanner.backend.model.Faculty;
import com.examplanner.backend.model.Hall;
import com.examplanner.backend.model.SeatAssignment;
import com.examplanner.backend.model.Student;
import com.examplanner.backend.repository.FacultyRepository;
import com.examplanner.backend.repository.HallRepository;
import com.examplanner.backend.repository.SeatAssignmentRepository;
import com.examplanner.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SeatingAlgorithmService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private SeatAssignmentRepository seatAssignmentRepository;

    public List<SeatAssignment> generateSeatingPlan() {

        // Step 1: Get all students, halls, faculty from DB
        List<Student> students = studentRepository.findAll();
        List<Hall> halls = hallRepository.findAll();
        List<Faculty> facultyList = facultyRepository.findAll();

        if (students.isEmpty() || halls.isEmpty()) {
            throw new RuntimeException("Add students and halls first!");
        }

        // Step 2: Group students by branch
        Map<String, Queue<Student>> branchMap = new LinkedHashMap<>();
        for (Student s : students) {
            branchMap.computeIfAbsent(s.getBranch(), k -> new LinkedList<>()).add(s);
        }

        // Step 3: Interleave students from different branches
        // This ensures no two adjacent students are from same branch
        List<Student> interleaved = new ArrayList<>();
        while (true) {
            boolean added = false;
            for (Queue<Student> queue : branchMap.values()) {
                if (!queue.isEmpty()) {
                    interleaved.add(queue.poll());
                    added = true;
                }
            }
            if (!added) break;
        }

        // Step 4: Clear old assignments
        seatAssignmentRepository.deleteAll();

        // Step 5: Assign students to seats hall by hall
        List<SeatAssignment> assignments = new ArrayList<>();
        int studentIndex = 0;

        for (Hall hall : halls) {
            for (int row = 0; row < hall.getRows(); row++) {
                for (int col = 0; col < hall.getColumns(); col++) {
                    if (studentIndex >= interleaved.size()) break;

                    SeatAssignment assignment = new SeatAssignment();
                    assignment.setRowNum(row + 1);
                    assignment.setColNum(col + 1);
                    assignment.setHallName(hall.getName());
                    assignment.setStudent(interleaved.get(studentIndex));
                    assignments.add(assignment);
                    studentIndex++;
                }
                if (studentIndex >= interleaved.size()) break;
            }
        }

        // Step 6: Save all assignments to DB
        seatAssignmentRepository.saveAll(assignments);

        return assignments;
    }

    public List<SeatAssignment> getSeatingPlan() {
        return seatAssignmentRepository.findAll();
    }
}