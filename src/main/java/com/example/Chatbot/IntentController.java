package com.example.Chatbot;

import org.springframework.web.bind.annotation.*; // Keep existing imports

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
// Add this annotation to allow requests from Angular's default development server
@CrossOrigin(origins = "http://localhost:4200") // Adjust port if your Angular runs elsewhere
public class IntentController {

    @PostMapping("/intent")
    // Your backend expects JSON, so @RequestBody is correct.
    public Map<String, String> getIntent(@RequestBody Map<String, String> payload) {
        String query = payload.get("query");
        // Basic input validation
        if (query == null || query.isBlank()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Query cannot be empty");
            // Consider returning a 400 Bad Request status code here too
            return errorResponse;
        }

        String intent = detectIntent(query);

        Map<String, String> response = new HashMap<>();
        response.put("intent", intent);
        return response;
    }

    // detectIntent method remains the same...
    private String detectIntent(String query) {
        query = query.toLowerCase();

        if (query.contains("data management plan") || query.contains("dmp")) { // Make keywords more specific
            return "dmp_tool_request";
        } else if (query.contains("deposit data") || query.contains("upload data")) {
            return "deposit_request";
        } else if (query.contains("data share")) {
            return "data_sharing";
        } else if (query.contains("doi") || query.contains("digital object identifier")) {
            return "doi_request";
        } else if (query.contains("help")) {
            return "help_request";
        } else {
            return "unknown_intent";
        }
    }
}