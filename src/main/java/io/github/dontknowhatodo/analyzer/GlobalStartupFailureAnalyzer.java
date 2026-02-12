package io.github.dontknowhatodo.analyzer;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class GlobalStartupFailureAnalyzer extends AbstractFailureAnalyzer<Exception> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, Exception cause) {
        String description = """
                             The application crashed during startup!
                             Error Type: """ + cause.getClass().getSimpleName() + "\n"
                + "Details: " + cause.getMessage();

        String action = """
                        Please review this troubleshooting checklist:
                          1. Database: Are your Supabase credentials correct in application.properties?
                          2. Port Conflict: Is another app running on port 8080? (Kill it if so).
                          3. Missing Beans: Did you forget a @Service, @Repository, or @Controller annotation?
                          4. Typo: Check your application.properties for spelling mistakes.
                        """;

        return new FailureAnalysis(description, action, cause);
    }
}
