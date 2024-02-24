package com.example.Form.Controller;


import com.example.Form.Model.Form.FormModel;
import com.example.Form.Model.Students;
import com.example.Form.Service.FormService;
import com.example.Form.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
public class pageController {
    private final FormService formService;
    private final StudentService studentService;

    @Autowired
    public pageController(FormService formService, StudentService studentService) {
        this.formService = formService;
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String indexPage(Model model) 
    {
        List<FormModel> forms = formService.getForms();
        List<FormModel> availableForms = new ArrayList<FormModel>();
        List<FormModel> upcomingForms = new ArrayList<FormModel>();
        for(FormModel form : forms){

            if(form.getReleaseDate() == null || form.getReleaseDate().after(new Date())){
                upcomingForms.add(form);
            }
            else {
                availableForms.add(form);

            }
        }
        model.addAttribute("available",availableForms.reversed());
        model.addAttribute("upcoming",upcomingForms.reversed());
        return "indexform";

    }

    //To create Form
    @GetMapping("/form")
    public String formcreatePage(){
        return "form";
    }

    //To Attend Form
    @GetMapping("/form/view/{id}")
    public String viewForm(@RequestParam Long id, Model model){
        Optional<FormModel> form = formService.getSingleForm(id);
        if(form.isPresent()){
            model.addAttribute("form",form);
            return "viewForm";
        }
        else {
            return "error";
        }
    }


    // To Edit the Form
    @GetMapping("/form/edit/{id}")
    public String editForm(@RequestParam Long id, Model model){
        Optional<FormModel> form = formService.getSingleForm(id);
        if(form.isPresent()){
            model.addAttribute("form",form);
            return "editForm";
        }
        else {
            return "error";
        }
    }

    @GetMapping("/success")
    public String successPage(){
        return "success";
    }
    // Student Login/Signup
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //Admin Login
    @GetMapping("/admin")
    public String adminLogin(){
        return "admin";
    }

    @GetMapping("/adminpanel")
    public String adminPanel(Model model){
        List<FormModel> forms = formService.getForms();
        model.addAttribute("forms",forms);
        List<Students> students = studentService.allStudent();
        return "adminform";
    }
}