package com.example.Form.Service;

import com.example.Form.Model.FormModel;
import com.example.Form.Repository.FormRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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
}
