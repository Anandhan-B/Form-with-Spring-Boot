package com.example.Form.Repository;

import com.example.Form.Model.Form.FormModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<FormModel,Long> {

}
