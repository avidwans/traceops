package controller;

import ai.LLMClient;
import mapper.ResponseMapper;
import model.ChatBotQuery;
import model.ExceptionAnalysis;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import util.LogParser;
import util.PromptBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/logs")
public class TraceOpsController {

    @GetMapping("/analyze")
    public ResponseEntity<List<ExceptionAnalysis>> analyze() throws IOException {
        Path logPath = Paths.get("src/main/resources/sample-log.txt");
        Map<String, List<String>> exceptionMap = LogParser.extractExceptions(logPath);
        String prompt = PromptBuilder.buildPrompt(exceptionMap);
        String llmResponse = LLMClient.sendPromptToModel(prompt);

        // You will need to implement parsing of llmResponse into ExceptionAnalysis objects.
        List<ExceptionAnalysis> results = ResponseMapper.parseLLMResponse(llmResponse);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody ChatBotQuery chatBotQuery) throws IOException {
        String logContent = new String(Files.readAllBytes(Paths.get("src/main/resources/sample-log.txt")));

        String response = LLMClient.queryChatBot(logContent, chatBotQuery.getQuery());

        return ResponseEntity.ok(response);

    }
}
