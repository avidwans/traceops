package util;

import java.util.List;
import java.util.Map;

public class PromptBuilder {
    public static String buildPrompt(Map<String, List<String>> exceptionMap) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Analyze the following Java exception stack traces. Group by <Exception class name>. Provide:\n")
                .append("**TraceOpsTrace** : <trace number>\n")
                .append("**Exception** : <Exception class name>\n")
                .append("**Number of occurrence** : <Number>\n")
                .append("**Root Cause** : <Max 2 lines>\n")
                .append("**Possible Solutions** : <Max 3 lines>\n\n")
                .append("Do not include any other text in the response\n\n")
                .append("Here are the stack traces:\n\n");

        for (List<String> traces : exceptionMap.values()) {
            for (String trace : traces) {
                prompt.append("```\n").append(trace).append("```\n\n");
            }
        }
        System.out.println(prompt.toString());
        return prompt.toString();
    }
}
