package com.library.app.dto;

public class ConversationRequest {
    public String sessionChat;
    public String conversation;

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public String getSessionChat() {
        return sessionChat;
    }

    public void setSessionChat(String sessionChat) {
        this.sessionChat = sessionChat;
    }
}
