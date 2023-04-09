package aaron.j.chatgpt.slackbot.model.slack;

import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.model.block.composition.TextObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.slack.api.Slack.getInstance;
import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;

@Component
@Slf4j
public class Slack {
    @Value(value = "${slack.token}")
    private String token;

    @Value(value = "${slack.channel.monitor}")
    private String channel;

    public void write(String message) {
        try {
            List<TextObject> textObjects = List.of(markdownText(message));
            
            MethodsClient methods = getInstance().methods(token);
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel(channel)
                    .blocks(asBlocks(
                            header(header -> header.text(plainText("결과"))),
                            divider(),
                            section(section -> section.fields(textObjects))))
                    .build();

            methods.chatPostMessage(request);
        } catch (SlackApiException | IOException e) {
            log.info("Slack Api Error");
        }
    }
}
