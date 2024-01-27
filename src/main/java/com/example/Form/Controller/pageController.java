package com.example.Form.Controller;

import com.example.Form.Model.FormModel;
import com.example.Form.Service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class pageController {
    private final FormService formService;

    @Autowired
    public pageController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("/form")
    public String indexPage(){
        return "index";
    }

    @PostMapping("/new")
    public String createForm(@RequestBody FormModel formModel){
        formService.createForm(formModel);
        return "redirect:/success";
    }
    @GetMapping("/success")
    public String successPage(){
        return "success";
    }
}
