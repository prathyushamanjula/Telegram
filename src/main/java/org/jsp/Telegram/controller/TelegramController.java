package org.jsp.Telegram.controller;

import org.jsp.Telegram.dto.TelegramMessageRequest;
import org.jsp.Telegram.dto.TelegramPhotoRequest;
import org.jsp.Telegram.dto.TelegramVideoRequest;
import org.jsp.Telegram.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TelegramController {

	@Autowired
	TelegramService telegramService;

	@PostMapping("/send-message")
	public void sendMessageToGroup(@RequestBody TelegramMessageRequest messageRequest) {
		telegramService.sendMessageToGroup(messageRequest.getChatId(), messageRequest.getMessage());
	}

	@PostMapping("/send-photo")
	public void sendPhotoToGroup(@RequestBody TelegramPhotoRequest photoRequest) {
		telegramService.sendPhotoToGroup(photoRequest.getChatId(), photoRequest.getPhotoUrl(), photoRequest.getCaption());
	}
	
	@PostMapping("/send-video")
    public void sendVideoToGroup(@RequestBody TelegramVideoRequest request) {
        telegramService.sendVideoToGroup(request.getChatId(), request.getVideoUrl(), request.getCaption());
    }
}
