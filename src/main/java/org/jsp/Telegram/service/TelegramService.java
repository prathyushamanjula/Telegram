package org.jsp.Telegram.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class TelegramService {

    @Value("${telegram.bot.token}")
    private String telegramBotToken;

    private final RestTemplate restTemplate;

    public TelegramService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendMessageToGroup(String chatId, String message) {
    	String telegramApiMessageUrl = "https://api.telegram.org/bot%s/sendMessage";
    	
        String url = String.format(telegramApiMessageUrl, telegramBotToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = String.format("{\"chat_id\": \"%s\", \"text\": \"%s\"}", chatId, message);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Message sent successfully!");
        } else {
            System.err.println("Failed to send message. Status code: " + response.getStatusCode());
        }
    }
    
    public void sendPhotoToGroup(String chatId, String photoUrl, String caption) {
    	String telegramApiPhotoUrl = "https://api.telegram.org/bot%s/sendPhoto";
    	
        String url = String.format(telegramApiPhotoUrl, telegramBotToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("chat_id", chatId);
        body.add("photo", photoUrl);
        body.add("caption", caption);

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Photo sent successfully!");
        } else {
            System.err.println("Failed to send photo. Status code: " + response.getStatusCode());
        }
    }
    
    public void sendVideoToGroup(String chatId, String videoUrl, String caption) {
        String telegramApiVideoUrl = "https://api.telegram.org/bot%s/sendVideo";
        String url = String.format(telegramApiVideoUrl, telegramBotToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("chat_id", chatId);
        body.add("caption", caption);
        body.add("video", videoUrl);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Video sent successfully!");
        } else {
            System.err.println("Failed to send video. Status code: " + response.getStatusCode().value());
        }
    }
}