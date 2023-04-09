package aaron.j.chatgpt.slackbot.controller.kafka;

import aaron.j.chatgpt.slackbot.service.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {
    private final KafkaProducer producer;

    @PostMapping
    public String sendMessage(String message) {
        producer.sendMessage(message);
        return "success";
    }
}
