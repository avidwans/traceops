package model;

public class ChatBotQuery {

    private String query;

    public ChatBotQuery(String query) {
        this.query = query;
    }

    public ChatBotQuery() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
