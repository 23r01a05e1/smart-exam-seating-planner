package com.examplanner.backend.service;

import com.examplanner.backend.model.Faculty;
import com.examplanner.backend.model.Hall;
import com.examplanner.backend.model.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelReaderService {

    // Read students from Excel
    public List<Student> readStudents(MultipartFile file) throws Exception {
        List<Student> students = new ArrayList<>();
        InputStream is = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Student student = new Student();
            student.setRollNumber(getCellValue(row.getCell(0)));
            student.setName(getCellValue(row.getCell(1)));
            student.setBranch(getCellValue(row.getCell(2)));
            student.setYear(getCellValue(row.getCell(3)));
            students.add(student);
        }
        workbook.close();
        return students;
    }

    // Read faculty from Excel
    public List<Faculty> readFaculty(MultipartFile file) throws Exception {
        List<Faculty> facultyList = new ArrayList<>();
        InputStream is = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Faculty faculty = new Faculty();
            faculty.setName(getCellValue(row.getCell(0)));
            faculty.setDepartment(getCellValue(row.getCell(1)));
            faculty.setMaxHalls(2); // default
            facultyList.add(faculty);
        }
        workbook.close();
        return facultyList;
    }

    // Read halls from Excel
    public List<Hall> readHalls(MultipartFile file) throws Exception {
        List<Hall> halls = new ArrayList<>();
        InputStream is = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Hall hall = new Hall();
            hall.setName(getCellValue(row.getCell(0)));
            int benches = (int) row.getCell(1).getNumericCellValue();
            hall.setCapacity(benches);
            // Calculate rows and columns from benches
            // Assume 2 students per bench, 6 columns
            hall.setColumns(6);
            hall.setRows((int) Math.ceil((benches * 2.0) / 6));
            halls.add(hall);
        }
        workbook.close();
        return halls;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((long) cell.getNumericCellValue());
        }
        return cell.getStringCellValue().trim();
    }
}