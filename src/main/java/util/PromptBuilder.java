package util;

import java.util.List;
import java.util.Map;

public class PromptBuilder {
    public static String buildPrompt(String logContent) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Analyze the following Java exception stack traces. Group by <Exception class name, Microservice>. Provide:\n")
                .append("**TraceOpsTrace** : <trace number>\n")
                .append("**Microservice : <Name of the microservice>\n")
                .append("**Exception** : <Exception class name>\n")
                .append("**Number of occurrence** : <Number>\n")
                .append("**Root Cause** : <Max 2 lines>\n")
                .append("**Possible Solutions** : <Max 2 lines>\n\n")
                .append("Do not include any other text in the response\n\n")
                .append("Same exception should not repeat for a microservice.  Repeating exceptions will be considered in count.")
                .append("Here is the log file::\n\n")
                .append(logContent);

        System.out.println(prompt.toString());
        return prompt.toString();
    }
}
