package com.devassist.devassistai.controller;

import com.devassist.devassistai.dto.DocumentQueryRequest;
import com.devassist.devassistai.dto.DocumentQueryResponse;
import com.devassist.devassistai.dto.DocumentUploadResponse;
import com.devassist.devassistai.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/docs")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService){
        this.documentService = documentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<DocumentUploadResponse>upload(
            @RequestParam("file")MultipartFile file)throws IOException{
        return ResponseEntity.ok(documentService.uploadDocument(file));
    }

    @PostMapping("/ask")
    public ResponseEntity<DocumentQueryResponse>ask(
            @Valid @RequestBody DocumentQueryRequest request){
        return ResponseEntity.ok(documentService.queryDocument(request));
    }



}
