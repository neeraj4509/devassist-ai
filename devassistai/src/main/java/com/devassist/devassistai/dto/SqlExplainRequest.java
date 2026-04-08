package com.devassist.devassistai.dto;

import jakarta.validation.constraints.NotBlank;

public class SqlExplainRequest {

    @NotBlank(message = "SQL query can not be empty")

    private String query;

    public String getQuery(){
        return query;
    }

    public void setQuery(String query){
        this.query = query;
    }
}
