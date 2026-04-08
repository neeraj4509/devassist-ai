package com.devassist.devassistai.service;

import com.devassist.devassistai.dto.CodeReviewRequest;
import com.devassist.devassistai.dto.CodeReviewResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class CodeReviewService {

    private final ChatClient chatClient;

    @Value("classpath:/promptTemplates/codeReviewPrompt.st")
    private Resource codeReviewPrompt;

    public CodeReviewService(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    public CodeReviewResponse review(CodeReviewRequest request){
        String review = chatClient
                .prompt()
                .user(spec -> spec
                    .text(codeReviewPrompt)
                    .param("language", request.getLanguage())
                    .param("code", request.getCode()))
                .call()
                .content();

        return new CodeReviewResponse(request.getLanguage(),review);
    }
}
