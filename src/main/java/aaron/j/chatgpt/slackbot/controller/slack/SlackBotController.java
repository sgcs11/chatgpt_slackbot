package aaron.j.chatgpt.slackbot.controller.slack;

import aaron.j.chatgpt.slackbot.service.kafka.KafkaProducer;
import aaron.j.chatgpt.slackbot.service.slack.SlackService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slack")
@RequiredArgsConstructor
@Slf4j
public class SlackBotController {
    private final SlackService slackService;

    private final KafkaProducer kafkaProducer;

    private final ObjectMapper mapper;

    @PostMapping("/chatgpt")
    public ResponseEntity<JsonNode> askToChatGpt(@RequestBody String request) {
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(request);
            String message = jsonNode.get("event").get("text").asText().split(" ",2)[1];
            kafkaProducer.sendMessage(message);
            return ResponseEntity.ok(jsonNode);
        } catch (Exception e) {
            log.info("요청 처리 실패");
            return ResponseEntity.ok().body(jsonNode); //Slack과의 연동을 위해 StatusCode는 200 성공으로 함
        }
    }
}
