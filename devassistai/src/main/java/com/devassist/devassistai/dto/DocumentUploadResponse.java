package com.devassist.devassistai.dto;

public class DocumentUploadResponse {
    private String fileName;
    private String status;
    private int totalChunks;

    public DocumentUploadResponse(String fileName, String status, int totalChunks){
        this.fileName = fileName;
        this.status = status;
        this.totalChunks = totalChunks;
    }

    public String getFileName(){
        return fileName;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public int getTotalChunks(){
        return totalChunks;
    }

    public void setTotalChunks(int totalChunks){
        this.totalChunks = totalChunks;
    }





}
