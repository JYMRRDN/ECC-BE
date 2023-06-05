package com.exist.ecctraining.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

    @PostMapping("/hello")
    public ResponseEntity<String> HelloWorld(){
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }
}
