package org.example;

public class ProjectDataDto {
    String client_id;
    String start_date;
    String finish_date;

    public ProjectDataDto(String client_id, String start_date, String finish_date) {
        this.client_id = client_id;
        this.start_date = start_date;
        this.finish_date = finish_date;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getFinish_date() {
        return finish_date;
    }
}
