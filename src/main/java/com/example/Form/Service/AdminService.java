package com.example.Form.Service;

import com.example.Form.Model.AdminModel;
import com.example.Form.Model.Students;
import com.example.Form.Repository.AdminRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    public List<AdminModel> allAdmin(){
        return adminRepository.findAll();
    }

    public AdminModel createAdmin(AdminModel admin){
        return  adminRepository.save(admin);
    }

    public ResponseEntity<String> loginAdmin(AdminModel admin){
        try {
            String email = admin.getEmail();
            String password = admin.getPassword();

            AdminModel user = adminRepository.findByEmail(email);
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

    public Optional<AdminModel> getOneAdmin(Long id) {
        return adminRepository.findById(id);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public void editAdmin(AdminModel oldAdmin, AdminModel newAdmin) {
        if(newAdmin.getEmail() != null) oldAdmin.setEmail(newAdmin.getEmail());
        if(newAdmin.getPassword() != null) oldAdmin.setPassword(newAdmin.getPassword());
        adminRepository.save(oldAdmin);
    }
}
