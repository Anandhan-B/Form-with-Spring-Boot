package com.example.Form.Controller;

import com.example.Form.Model.ChoiceModel;
import com.example.Form.Model.FormModel;
import com.example.Form.Model.QuestionModel;
import com.example.Form.Service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    // To get All Forms
    @GetMapping("/all")
    public List<FormModel> getForms(){
        return formService.getForms();
    }

    @PostMapping("/new")
    public ResponseEntity<String> createForm(@RequestBody FormModel formModel){
        String title = formModel.getTitle();
        List<QuestionModel> questions = formModel.getQuestions();
        if(title == null || title.isBlank() || questions == null || questions.isEmpty()){
            return new ResponseEntity<>("Data should not be Empty",HttpStatus.BAD_REQUEST);
        }
        for(QuestionModel question : questions){
            String q = question.getQuestion();
            String type = question.getType();
            Integer totalMark = question.getTotalMark();
            if(q == null || q.isBlank() || type == null || type.isBlank() || totalMark == null){
                return new ResponseEntity<>("Empty values not allowed",HttpStatus.BAD_REQUEST);
            }
            if(totalMark < 0){
                return new ResponseEntity<>("Negative values not allowed",HttpStatus.BAD_REQUEST);
            }
            for(ChoiceModel choice : question.getChoices()){
                String ch = choice.getChoice();
                Integer mark = choice.getMark();
                Boolean ans = choice.getAns();
                if(ch == null || ch.isBlank() || mark == null || ans == null){
                    return new ResponseEntity<>("Empty values not allowed",HttpStatus.BAD_REQUEST);
                }
                if(mark < 0){
                    return new ResponseEntity<>("Negative values not allowed",HttpStatus.BAD_REQUEST);
                }
            }
        }
        formService.createForm(formModel);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
