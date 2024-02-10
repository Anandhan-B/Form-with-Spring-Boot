package com.example.Form.Service;

import com.example.Form.Model.FormModel;
import com.example.Form.Repository.FormRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class FormService {
    private final FormRepository formRepository;
    @Autowired
    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }
    public FormModel createForm(FormModel formModel){
        return formRepository.save(formModel);
    }

    public List<FormModel> getForms() {
        return formRepository.findAll();
    }

    public ResponseEntity<String> deleteForm(Long id) {
        try{
            formRepository.deleteById(id);
            return new ResponseEntity<>("Form Deleted", HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
