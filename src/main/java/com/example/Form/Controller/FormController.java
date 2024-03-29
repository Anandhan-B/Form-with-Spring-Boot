package com.example.Form.Controller;

import com.example.Form.Model.Form.ChoiceModel;
import com.example.Form.Model.Form.FormModel;
import com.example.Form.Model.Form.QuestionModel;
import com.example.Form.Service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        try {
            String title = formModel.getTitle();
            List<QuestionModel> questions = formModel.getQuestions();
            Date release = formModel.getReleaseDate();
            if(release == null ){
                formModel.setReleaseDate(new Date());
            }
            if (title == null || title.isBlank() || questions == null || questions.isEmpty()) {
                return new ResponseEntity<>("Data should not be Empty", HttpStatus.BAD_REQUEST);
            }
            for (QuestionModel question : questions) {
                String q = question.getQuestion();
                String type = question.getType();
                Integer totalMark = question.getTotalMark();
                if (q == null || q.isBlank() || type == null || type.isBlank() || totalMark == null) {
                    return new ResponseEntity<>("Empty values not allowed", HttpStatus.BAD_REQUEST);
                }
                if (totalMark < 0) {
                    return new ResponseEntity<>("Negative values not allowed", HttpStatus.BAD_REQUEST);
                }
                for (ChoiceModel choice : question.getChoices()) {
                    String ch = choice.getChoice();
                    Integer mark = choice.getMark();
                    Boolean ans = choice.getAns();
                    if (ch == null || ch.isBlank() || mark == null || ans == null) {
                        return new ResponseEntity<>("Empty values not allowed", HttpStatus.BAD_REQUEST);
                    }
                    if (mark < 0) {
                        return new ResponseEntity<>("Negative values not allowed", HttpStatus.BAD_REQUEST);
                    }
                }
            }
            formService.createForm(formModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editForm(@RequestParam Long id, @RequestBody FormModel newForm){
        try{
        Optional<FormModel> form = formService.getSingleForm(id);
        if(form.isPresent()){
            FormModel oldForm = form.get();
            formService.editForm(oldForm,newForm);
            return new ResponseEntity<>("Form Updated Successfully",HttpStatus.OK);
        }
        return  new ResponseEntity<>("Form Not Found",HttpStatus.NOT_FOUND);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteForm(@RequestParam Long id){
        try{
        formService.deleteForm(id);
        return new ResponseEntity<>("Form Deleted",HttpStatus.ACCEPTED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
