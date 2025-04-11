package com.estsoft.restapiaccessdata.controller;

import com.estsoft.restapiaccessdata.service.ExternalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExternalApiController {

    private final ExternalService externalService;

    public ExternalApiController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @GetMapping("/api/externalArticles")
    public ResponseEntity<Void> getExternalArticleData() {
        externalService.getArticles();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/externalComments")
    public ResponseEntity<Void> getExternalComments() {
        externalService.getComments();

        return ResponseEntity.ok().build();
    }
}
