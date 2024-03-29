package com.example.Form.Service;

import com.example.Form.Model.Form.FormModel;
import com.example.Form.Repository.FormRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<FormModel> getSingleForm(Long id) {
        return formRepository.findById(id);

    }

    public void editForm(FormModel oldForm, FormModel newForm) {
        if (newForm.getTitle() != null) oldForm.setTitle(newForm.getTitle());
        if (newForm.getQuestions() != null) oldForm.setQuestions(newForm.getQuestions());
        formRepository.save(oldForm);
    }
}
