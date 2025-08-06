package mapper;

import model.ExceptionAnalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseMapper {

    public static List<ExceptionAnalysis> parseLLMResponse(String response) {
        List<ExceptionAnalysis> results = new ArrayList<>();

        // Split the response into traces using "---" or "**Trace"
        String[] traces = response.split("TraceOpsTrace");

        for (String trace : traces) {
            if (trace.trim().isEmpty()) continue;

            ExceptionAnalysis ea = new ExceptionAnalysis();

            // Use regex to extract fields
            Pattern exceptionPattern = Pattern.compile("\\*\\*Exception:\\*\\*\\s*(.*)");
            Pattern occurrencePattern = Pattern.compile("\\*\\*Number of occurrence:\\*\\*\\s*(\\d+)");
            Pattern rootCausePattern = Pattern.compile("\\*\\*Root Cause:\\*\\*\\s*(.*)");
            Pattern solutionsPattern = Pattern.compile("\\*\\*Possible Solutions:\\*\\*\\s*(.*)", Pattern.DOTALL);
            Pattern microServicePattern = Pattern.compile("\\*\\*Microservice:\\*\\*\\s*(.*)");

            Matcher m;

            m = exceptionPattern.matcher(trace);
            if (m.find()) ea.setException(m.group(1).trim());

            m = occurrencePattern.matcher(trace);
            if (m.find()) ea.setOccurrence(Integer.parseInt(m.group(1).trim()));

            m = rootCausePattern.matcher(trace);
            if (m.find()) ea.setRootCause(m.group(1).trim());

            m = solutionsPattern.matcher(trace);
            if (m.find()) ea.setPossibleSolutions(m.group(1).trim());

            m = microServicePattern.matcher(trace);
            if(m.find()) ea.setMicroservices(m.group(1).trim());

            if(Objects.nonNull(ea.getException())) {
                results.add(ea);
            }
        }

        return results;
    }

}
