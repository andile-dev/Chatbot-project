package com.example.Chatbot.controller;

import com.example.Chatbot.service.intentservice.IntentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public  class Intent {
    @Autowired
    private IntentService intentService;

    @GetMapping("/getintent/{user_input}")
    public ResponseEntity<String>  getIntent(@PathVariable String user_input ){

        return ResponseEntity.ok(intentService.getIntent(user_input));
    }
}
