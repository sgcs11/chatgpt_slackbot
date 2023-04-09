package aaron.j.chatgpt.slackbot.service.kafka;

import aaron.j.chatgpt.slackbot.service.slack.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final SlackService slackService;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        slackService.slackWriteChatGptAnswer(message);
        System.out.printf("Consumed message : %s%n", message);
    }
}
