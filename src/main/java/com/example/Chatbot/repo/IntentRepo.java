package com.example.Chatbot.repo;


import com.example.Chatbot.model.Intent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntentRepo extends JpaRepository<Intent,Long> {

}
