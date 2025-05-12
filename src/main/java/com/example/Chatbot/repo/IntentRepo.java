package com.example.Chatbot.repo;


import com.example.Chatbot.model.Intent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntentRepo extends JpaRepository<Intent,Long> {

    @Query(value = "SELECT u FROM Intent u WHERE u.utterance LIKE %:query% ")
    List<Intent> searchIntent(@Param("query") String query);
}
