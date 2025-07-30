package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogParser {
    public static Map<String, List<String>> extractExceptions(Path logFile) throws IOException {
        // Simplified logic — match blocks starting with "Exception in thread" or containing common exceptions
        Map<String, List<String>> exceptionMap = new HashMap<>();
        List<String> lines = Files.readAllLines(logFile);
        StringBuilder currentTrace = new StringBuilder();
        String currentException = null;

        for (String line : lines) {
            if (line.matches(".*(Exception|Error).*")) {
                if (currentTrace.length() > 0 && currentException != null) {
                    exceptionMap.computeIfAbsent(currentException, k -> new ArrayList<>()).add(currentTrace.toString());
                }
                currentTrace = new StringBuilder(line + "\n");
                currentException = extractExceptionClass(line);
            } else if (currentTrace.length() > 0) {
                currentTrace.append(line).append("\n");
            }
        }

        // Add last
        if (currentTrace.length() > 0 && currentException != null) {
            exceptionMap.computeIfAbsent(currentException, k -> new ArrayList<>()).add(currentTrace.toString());
        }

        return exceptionMap;
    }

    private static String extractExceptionClass(String line) {
        int idx = line.indexOf(":");
        if (idx > -1) return line.substring(0, idx).trim();
        return line.trim();
    }
}
