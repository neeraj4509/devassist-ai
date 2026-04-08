package com.devassist.devassistai.controller;

import com.devassist.devassistai.dto.SqlExplainRequest;
import com.devassist.devassistai.dto.SqlExplainResponse;
import com.devassist.devassistai.service.SqlExplainerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/sql")
public class SqlExplainerController {

    private final SqlExplainerService sqlExplainerService;

    public SqlExplainerController(SqlExplainerService sqlExplainerService){
        this.sqlExplainerService =sqlExplainerService;
    }

    @PostMapping("/explain")
    public ResponseEntity<SqlExplainResponse> explain(
            @Valid @RequestBody SqlExplainRequest request){
        return ResponseEntity.ok(sqlExplainerService.explain(request));
    }
}
