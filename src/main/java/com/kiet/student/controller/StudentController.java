package com.kiet.student.controller;
import com.kiet.student.model.Student;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@RestController
public class StudentController {
List<Student> students = new ArrayList<>();

public StudentController() {
    students.add(new Student(101, "Rahul Sharma", "rahul@kiet.edu", "CSE", 2, 8.65));
    students.add(new Student(102, "Priya Singh", "priya@kiet.edu", "ECE", 2, 9.10));
    students.add(new Student(103, "Aman Kumar", "aman@kiet.edu", "CSE", 3, 8.20));
    students.add(new Student(104, "Neha Gupta", "neha@kiet.edu", "IT", 2, 9.25));
    students.add(new Student(105, "Rohit Verma", "rohit@kiet.edu", "ECE", 3, 7.80));
}

@GetMapping("/students")
public List<Student> getAllStudents() {
    return students;
}

@GetMapping("/students/{studentId}")
public Student getStudentById(@PathVariable int studentId) {
    for (Student s : students) {
        if (s.getStudentId() == studentId) {
            return s;
        }
    }
    return null;
}
@PostMapping("/students")
public Student addStudent(@RequestBody Student student) {
students.add(student);
return student;
}
@PutMapping("/students/{studentId}")
public Object updateStudent(@PathVariable int studentId,
                            @RequestBody Student updatedStudent) {

    for (Student s : students) {
        if (s.getStudentId() == studentId) {

            s.setName(updatedStudent.getName());
            s.setEmail(updatedStudent.getEmail());
            s.setBranch(updatedStudent.getBranch());
            s.setYear(updatedStudent.getYear());
            s.setCgpa(updatedStudent.getCgpa());

            return s;
        }
    }

    return java.util.Map.of("message", "Student not found");
}

@DeleteMapping("/students/{studentId}")
public Object deleteStudent(@PathVariable int studentId) {

    for (Student s : students) {
        if (s.getStudentId() == studentId) {
            students.remove(s);
            return java.util.Map.of("message", "Student deleted successfully");
        }
    }

    return java.util.Map.of("message", "Student not found");
}

}


