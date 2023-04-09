package aaron.j.chatgpt.slackbot.service.slack;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SlackServiceTest {
    @Autowired
    SlackService slackService;

    @Test
    void write_ChatGpt_Answer_To_Slack() {
        String question = "테스트";
        slackService.slackWriteChatGptAnswer(question);
    }
}
