package com.example.Form.Controller;

import com.example.Form.Model.Students;
import com.example.Form.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/one/{id}")
    public ResponseEntity<?> getOneStudent(@RequestParam Long id){
         try {
            Optional<Students> student = studentService.getOneStudent(id);
            if(student.isPresent()){
             return new ResponseEntity<>(student.get(),HttpStatus.OK);
            }
             return new ResponseEntity<>("Student Not Found",HttpStatus.NOT_FOUND);
         }catch (Exception e){
             e.printStackTrace();
             return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
         }
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
    @DeleteMapping("/del/{id}")
    public  ResponseEntity<String> deleteStudent(@RequestParam Long id){
        try{
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Student Deleted",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editStudent(@RequestParam Long id,@RequestBody Students newStudent){
        try{
            Optional<Students> student = studentService.getOneStudent(id);
            if(student.isPresent()){
                Students oldStudent = student.get();
                studentService.editStudent(oldStudent,newStudent);
                return new ResponseEntity<>("Student Updated Successfully",HttpStatus.OK);
            }
            return new ResponseEntity<>("Student Not Found",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginStudent(@RequestBody Students student){
        return studentService.loginStudent(student);
    }
}
