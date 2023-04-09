package aaron.j.chatgpt.slackbot.model.slack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SlackTest {
    @Mock
    Slack slack;

    @Test
    void write_to_Slack() {
        String message = "테스트";

        slack.write(message);
    }
}
