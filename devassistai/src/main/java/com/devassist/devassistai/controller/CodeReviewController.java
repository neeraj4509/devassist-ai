package com.devassist.devassistai.controller;

import com.devassist.devassistai.dto.CodeReviewRequest;
import com.devassist.devassistai.dto.CodeReviewResponse;
import com.devassist.devassistai.service.CodeReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/code")
public class CodeReviewController {

    private final CodeReviewService codeReviewService;

    public CodeReviewController(CodeReviewService codeReviewService){
        this.codeReviewService = codeReviewService;
    }

    @PostMapping("/review")
    public ResponseEntity<CodeReviewResponse>review(
            @Valid @RequestBody CodeReviewRequest request){
        return ResponseEntity.ok(codeReviewService.review(request));
    }

}
