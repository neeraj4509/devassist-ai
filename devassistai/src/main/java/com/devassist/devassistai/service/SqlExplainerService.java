package com.devassist.devassistai.service;

import com.devassist.devassistai.dto.SqlExplainRequest;
import com.devassist.devassistai.dto.SqlExplainResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class SqlExplainerService {

    private final ChatClient chatClient;

    @Value("classpath:/promptTemplates/sqlExplainPrompt.st")
    private Resource sqlExplainPrompt;

    public SqlExplainerService(ChatClient chatClient){
        this.chatClient =chatClient;
    }

    public SqlExplainResponse explain(SqlExplainRequest request){
        String explanation = chatClient
                .prompt()
                .user(spec->spec
                        .text(sqlExplainPrompt)
                        .param("query", request.getQuery()))
                .call()
                .content();

        return new SqlExplainResponse(request.getQuery(),explanation);
    }

}
