package aaron.j.chatgpt.slackbot.service.kafka;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    @Value(value = "${kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("produced_message", message);
        log.info(jsonObject.toString());
        this.kafkaTemplate.send(topicName, message);
    }
}
