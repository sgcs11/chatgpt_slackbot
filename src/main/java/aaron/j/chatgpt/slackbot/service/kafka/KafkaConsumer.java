package aaron.j.chatgpt.slackbot.service.kafka;

import aaron.j.chatgpt.slackbot.service.slack.SlackService;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
    private final SlackService slackService;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("consumed_message", message);
        log.info(jsonObject.toString());
        slackService.slackWriteChatGptAnswer(message);
    }
}
