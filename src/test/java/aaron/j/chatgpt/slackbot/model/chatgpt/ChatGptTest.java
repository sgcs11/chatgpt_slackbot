package aaron.j.chatgpt.slackbot.model.chatgpt;

import aaron.j.chatgpt.slackbot.dto.chatgpt.ChatGptResponseDto;
import aaron.j.chatgpt.slackbot.dto.chatgpt.Choice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ChatGptTest {
    @Mock
    ChatGpt chatGpt;

    @Test
    void ask_to_ChatGpt() {
        String question = "질문";
        String answer = "대답";
        ChatGptResponseDto response = ChatGptResponseDto.builder().choices(List.of(Choice.builder().text(answer).build())).build();
        Mockito.when(chatGpt.askQuestion(question)).thenReturn(response);

        assertThat(chatGpt.askQuestion(question).getChoices().get(0).getText()).isEqualTo(answer);
    }
}
