package model;

public class ExceptionAnalysis {

    private String exception;
    private int occurrence;
    private String rootCause;
    private String possibleSolutions;

    public ExceptionAnalysis() {
    }

    public ExceptionAnalysis(String exception, int occurrence, String rootCause, String possibleSolutions) {
        this.exception = exception;
        this.occurrence = occurrence;
        this.rootCause = rootCause;
        this.possibleSolutions = possibleSolutions;
    }

    // Constructors, getters, setters

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
