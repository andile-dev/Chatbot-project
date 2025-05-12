package com.example.Chatbot.service;

import com.example.Chatbot.model.Intent;
import com.example.Chatbot.repo.IntentRepo;
import com.example.Chatbot.service.intentservice.IntentService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IntentServiceImp implements IntentService {
@Autowired
private IntentRepo intentRepo;
    @Override
    public String getIntent(String user_input) {
        List<Intent> intent=null;

        Map<String,String>map=new HashMap<>();

        if(!(user_input.equals(null))){
            try{

               intent= intentRepo.searchIntent(user_input);

                map.put(intent.toString(),intent.toString());

            }
            catch (NullPointerException e){
                    e.getMessage();
            }
        }



        return map.toString();
    }
}
