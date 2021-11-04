package com.test.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    @GetMapping("test")
    public ResponseEntity<?> test()
            throws Exception {
        return ResponseEntity.ok("SUCCESS");
    }
}
