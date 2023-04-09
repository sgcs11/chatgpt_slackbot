package aaron.j.chatgpt.slackbot.service.slack;

import aaron.j.chatgpt.slackbot.dto.chatgpt.ChatGptResponseDto;
import aaron.j.chatgpt.slackbot.model.chatgpt.ChatGpt;
import aaron.j.chatgpt.slackbot.model.slack.Slack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class SlackService {
    private final ChatGpt chatGpt;
    private final Slack slack;

    public void slackWriteChatGptAnswer(String question) {
        ChatGptResponseDto chatGptResponseDto = chatGpt.askQuestion(question);
        String answer = chatGptResponseDto.getChoices().get(0).getText();
        slack.write(answer);
    }
}
