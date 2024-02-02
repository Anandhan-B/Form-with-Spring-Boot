package com.example.Form.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "choices")
public class ChoiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 500)
    private String choice;
    @Column(nullable = false)
    private Integer mark;
    @Column(nullable = false)
    private Boolean ans;

    public ChoiceModel(String choice, Integer mark, Boolean ans) {
        this.choice = choice;
        this.mark = mark;
        this.ans = ans;
    }

    public ChoiceModel() {
    }

    @Override
    public String toString() {
        return "ChoiceModel{" +
                "id=" + id +
                ", choice='" + choice + '\'' +
                ", mark=" + mark +
                ", ans=" + ans +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Boolean getAns() {
        return ans;
    }

    public void setAns(Boolean ans) {
        this.ans = ans;
    }
}
