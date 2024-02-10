package com.example.Form.Controller;


import com.example.Form.Service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


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