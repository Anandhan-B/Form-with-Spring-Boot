package com.example.Form.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "questions")
public class QuestionModel {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false,length = 1000)
   private String question;
   @Column(nullable = false)
   private String type;
   @Column(nullable = false)
   private Integer totalMark;
   @OneToMany(cascade = CascadeType.ALL)
   @JoinColumn(name = "question_id")
   private List<ChoiceModel> choices;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public QuestionModel() {
   }

   public String getQuestion() {
      return question;
   }

   public void setQuestion(String question) {
      this.question = question;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public Integer getTotalMark() {
      return totalMark;
   }

   public void setTotalMark(Integer totalMark) {
      this.totalMark = totalMark;
   }

   public List<ChoiceModel> getChoices() {
      return choices;
   }

   public void setChoices(List<ChoiceModel> choices) {
      this.choices = choices;
   }

   public QuestionModel(String question, String type, Integer totalMark, List<ChoiceModel> choices) {
      this.question = question;
      this.type = type;
      this.totalMark = totalMark;
      this.choices = choices;
   }

   @Override
   public String toString() {
      return "QuestionModel{" +
              "id=" + id +
              ", question='" + question + '\'' +
              ", type='" + type + '\'' +
              ", totalMark=" + totalMark +
              ", choices=" + choices +
              '}';
   }
}

