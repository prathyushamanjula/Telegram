package org.jsp.Telegram.dto;

import lombok.Data;

@Data
public class TelegramMessageRequest {
	
	private String chatId;
    private String message;
    
    public TelegramMessageRequest(String chatId, String message) {
        this.chatId = chatId;
        this.message = message;
    }   
}
