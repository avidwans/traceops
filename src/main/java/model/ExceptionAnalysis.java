package model;

public class ExceptionAnalysis {

    private String exception;
    private int occurrence;
    private String rootCause;
    private String possibleSolutions;
    private String microservices;

    public ExceptionAnalysis() {
    }

    public ExceptionAnalysis(String exception, int occurrence, String rootCause, String possibleSolutions, String microservices) {
        this.exception = exception;
        this.occurrence = occurrence;
        this.rootCause = rootCause;
        this.possibleSolutions = possibleSolutions;
        this.microservices = microservices;
    }

    // Constructors, getters, setters


    public String getMicroservices() {
        return microservices;
    }

    public void setMicroservices(String microservices) {
        this.microservices = microservices;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public String getPossibleSolutions() {
        return possibleSolutions;
    }

    public void setPossibleSolutions(String possibleSolutions) {
        this.possibleSolutions = possibleSolutions;
    }
}
