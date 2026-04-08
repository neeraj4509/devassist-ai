package com.devassist.devassistai.dto;

public class CodeReviewResponse {

    private String language;
    private String review;

    public CodeReviewResponse(String language, String review){
        this.language = language;
        this.review = review;
    }

    public String getLanguage(){
        return language;
    }

    public void setLanguage(String language){
        this.language = language;
    }
    
    public String getReview(){
        return review;
    }

    public void setReview(String review){
        this.review = review;
    }
}
