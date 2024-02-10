package com.example.Form.Service;

import com.example.Form.Model.Students;
import com.example.Form.Repository.StudentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Data
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Students> allStudent(){
        return studentRepository.findAll();
    }

    public Students createStudent(Students student){
        return  studentRepository.save(student);
    }
    public ResponseEntity<String> loginStudent(Students student){
        try {
            String email = student.getEmail();
            String password = student.getPassword();

            Students user = studentRepository.findByEmail(email);
            if (user != null) {
                if(Objects.equals(user.getPassword(), password)){
                    return new ResponseEntity<>("Login Success", HttpStatus.ACCEPTED);
                }
                else {
                    return new ResponseEntity<>("Wrong Password", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Email not found", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
