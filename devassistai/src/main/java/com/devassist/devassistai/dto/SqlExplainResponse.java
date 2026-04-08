package com.devassist.devassistai.dto;

public class SqlExplainResponse {

    private String query;
    private String explanation;

    public SqlExplainResponse(String query, String explanation){
        this.query = query;
        this.explanation = explanation;
    }

    public String getQuery(){
        return query;
    }

    public void setQuery(String query){
        this.query = query;
    }

    public String getExplanation(){
        return explanation;
    }

    public void setExplanation(String explanation){
        this.explanation = explanation;
    }
}
