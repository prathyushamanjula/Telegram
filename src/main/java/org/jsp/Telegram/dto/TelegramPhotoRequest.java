package org.jsp.Telegram.dto;

import lombok.Data;

@Data
public class TelegramPhotoRequest {
	private String chatId;
    private String photoUrl;
    private String caption;

    public TelegramPhotoRequest(String chatId, String photoUrl, String caption) {
        this.chatId = chatId;
        this.photoUrl = photoUrl;
        this.caption = caption;
    }
}
