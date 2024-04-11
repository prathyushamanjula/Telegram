package org.jsp.Telegram.dto;

import lombok.Data;

@Data
public class TelegramVideoRequest {
    private String chatId;
    private String videoUrl;
    private String caption;
}
