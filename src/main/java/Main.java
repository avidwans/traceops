import ai.LLMClient;
import mapper.ResponseMapper;
import model.ExceptionAnalysis;
import pdf.PDFGenerator;
import util.LogParser;
import util.PromptBuilder;

import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Path logPath = Paths.get("src/main/resources/sample-log.txt");
        Map<String, List<String>> exceptionMap = LogParser.extractExceptions(logPath);
        String prompt = PromptBuilder.buildPrompt(exceptionMap);
        String llmResponse = LLMClient.sendPromptToModel(prompt);

        // You will need to implement parsing of llmResponse into ExceptionAnalysis objects.
        List<ExceptionAnalysis> results = ResponseMapper.parseLLMResponse(llmResponse);

        PDFGenerator.generate(results, "Exception_Report.pdf");
        System.out.println("PDF generated.");
    }
}