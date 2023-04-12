package aaron.j.chatgpt.slackbot.model.chatgpt;

import aaron.j.chatgpt.slackbot.config.chatgpt.ChatGptConfig;
import aaron.j.chatgpt.slackbot.dto.chatgpt.ChatGptRequestDto;
import aaron.j.chatgpt.slackbot.dto.chatgpt.ChatGptResponseDto;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class ChatGpt {
    @Value(value = "${chatgpt.token}")
    private String token;
    private final RestTemplate restTemplate = new RestTemplate();

    private HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + token);
        return new HttpEntity<>(requestDto, headers);
    }

    private ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity) {
        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatGptRequestDtoHttpEntity,
                ChatGptResponseDto.class);

        return responseEntity.getBody();
    }

    public ChatGptResponseDto askQuestion(String message) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("question", message);
        log.info(jsonObject.toString());

        ChatGptResponseDto response = this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequestDto(
                                ChatGptConfig.MODEL,
                                message,
                                ChatGptConfig.MAX_TOKEN,
                                ChatGptConfig.TEMPERATURE,
                                ChatGptConfig.TOP_P
                        )
                )
        );
        jsonObject = new JsonObject();
        jsonObject.addProperty("answer", response.getChoices().get(0).getText());
        log.info(jsonObject.toString());

        return response;
    }
}
