package com.example.Form.Controller;

import com.example.Form.Model.FormModel;
import com.example.Form.Service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/form")
public class FormController {
    private final FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }
    @GetMapping("/all")
    public List<FormModel> getForms(){
        return formService.getForms();
    }
    /*@PostMapping("/new")
    public FormModel createForm(@RequestBody FormModel formModel){
        return formService.createForm(formModel);
    }*/
}
