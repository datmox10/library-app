package com.library.app.dto;

public class ConversationResponse {
    public String message;

    public ConversationResponse(String message) {
        this.message = message;
    }

    public ConversationResponse() {
        
    }

    @Override
    public String toString() {
        return "ConversationResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
