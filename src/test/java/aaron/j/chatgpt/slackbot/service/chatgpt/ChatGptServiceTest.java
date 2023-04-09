package aaron.j.chatgpt.slackbot.service.chatgpt;

import aaron.j.chatgpt.slackbot.dto.chatgpt.ChatGptResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ChatGptServiceTest {
    @Autowired
    ChatGptService chatGptService;

    @Test
    void ask_To_ChatGpt() {
        String question = "테스트";

        ChatGptResponseDto chatGptResponseDto = chatGptService.askQuestion(question);
        assertThat(chatGptResponseDto.getChoices().get(0).getText()).isNotEmpty();
    }
}