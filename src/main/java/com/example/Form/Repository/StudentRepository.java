package com.example.Form.Repository;

import com.example.Form.Model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Students,Long> {
    Students findByEmail(String Email);

}
