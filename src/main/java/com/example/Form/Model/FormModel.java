package com.example.Form.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Forms")
public class FormModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String[] questions;
}
