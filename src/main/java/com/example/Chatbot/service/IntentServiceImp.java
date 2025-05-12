package com.example.Chatbot.service;

import com.example.Chatbot.service.intentservice.IntentService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class IntentServiceImp implements IntentService {

    @Override
    public Map<String, String> getIntent(String user_input) {

        return Map.of();
    }
}
