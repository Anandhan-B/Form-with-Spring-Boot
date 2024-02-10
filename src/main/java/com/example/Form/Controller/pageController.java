package com.example.Form.Controller;


import com.example.Form.Model.FormModel;
import com.example.Form.Service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class pageController {
    private final FormService formService;

    @Autowired
    public pageController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("")
    public String indexPage(){
        return "index";
    }
    @GetMapping("/form")
    public String formcreatePage(){
        return "form";
    }

    @GetMapping("/form/view/{id}")
    public String viewForm(@RequestParam Long id){
        Optional<FormModel> form = formService.getSingleForm(id);
        if(form.isPresent()){
            return "viewForm";
        }
        else {

            return "error";
        }
    }

    // To create new Form
    @GetMapping("/success")
    public String successPage(){
        return "success";
    }
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/admin")
    public String adminLogin(){
        return "admin";
    }
}