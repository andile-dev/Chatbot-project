package com.example.Chatbot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Intent")
@Getter
@Setter
public class Intent {
    @Id
    @GeneratedValue
    private Long id;
    private String utterance;
    private String intent;
}
