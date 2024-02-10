package com.example.Form.Controller;

import com.example.Form.Model.Students;
import com.example.Form.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student/")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/all")
    public List<Students> allStudents(){
        return studentService.allStudent();
    }
    @PostMapping("/new")
    public ResponseEntity<String> createStudent(@RequestBody Students student){
        try {
            studentService.createStudent(student);
            return new ResponseEntity<>("Student Account Created",HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginStudent(@RequestBody Students student){
        return studentService.loginStudent(student);
    }
}
