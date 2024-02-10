package com.example.Form.Repository;

import com.example.Form.Model.AdminModel;
import com.example.Form.Model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel,Long> {
    AdminModel findByEmail(String email);
}
