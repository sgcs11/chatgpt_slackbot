package aaron.j.chatgpt.slackbot.service.chatgpt;

import aaron.j.chatgpt.slackbot.dto.chatgpt.ChatGptResponseDto;
import aaron.j.chatgpt.slackbot.model.chatgpt.ChatGpt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatGptService {
    private final ChatGpt chatGpt;
    public ChatGptResponseDto askQuestion(String message) {
        return chatGpt.askQuestion(message);
    }
}