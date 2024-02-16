package com.example.Form.Controller;

import com.example.Form.Model.AdminModel;
import com.example.Form.Model.Students;
import com.example.Form.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    private final AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @GetMapping("/all")
    public List<AdminModel> allAdmins(){
        return adminService.allAdmin();
    }
    @GetMapping("/one/{id}")
    public ResponseEntity<?> getOneAdmin(@RequestParam Long id){
        try {
            Optional<AdminModel> admin = adminService.getOneAdmin(id);
            if(admin.isPresent()){
                return new ResponseEntity<>(admin.get(),HttpStatus.OK);
            }
            return new ResponseEntity<>("Admin Not Found",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @DeleteMapping("/del/{id}")
    public  ResponseEntity<String> deleteAdmin(@RequestParam Long id){
        try{
            adminService.deleteAdmin(id);
            return new ResponseEntity<>("Admin Deleted",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editStudent(@RequestParam Long id,@RequestBody AdminModel newAdmin){
        try{
            Optional<AdminModel> admin = adminService.getOneAdmin(id);
            if(admin.isPresent()){
                AdminModel oldAdmin = admin.get();
                adminService.editAdmin(oldAdmin,newAdmin);
                return new ResponseEntity<>("Admin Updated Successfully",HttpStatus.OK);
            }
            return new ResponseEntity<>("Admin Not Found",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginStudent(@RequestBody AdminModel admin){
        return adminService.loginAdmin(admin);
    }
}
