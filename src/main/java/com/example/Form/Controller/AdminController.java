package com.example.Form.Controller;

import com.example.Form.Model.AdminModel;
import com.example.Form.Model.Students;
import com.example.Form.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    private final AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @GetMapping("/all")
    public List<AdminModel> allStudents(){
        return adminService.allAdmin();
    }
    @PostMapping("/new")
    public ResponseEntity<String> createStudent(@RequestBody AdminModel admin){
        try {
            adminService.createAdmin(admin);
            return new ResponseEntity<>("Admin Account Created", HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginStudent(@RequestBody AdminModel admin){
        return adminService.loginAdmin(admin);
    }
}
