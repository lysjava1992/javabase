package com.learn.springboot.chapter4.controller;

import com.learn.springboot.chapter4.entity.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class MockTestController {

    @GetMapping(value = "/mock/a")
    public String mockTestA(@RequestParam("a") String a,
                            @RequestParam("b") String b) {
        return a + b;
    }

    @PostMapping(value = "/mock/b")
    public String mockTestB(@RequestParam(value = "a", defaultValue = "1") String a,
                            @RequestParam(value = "b", defaultValue = "2") String b) {
        return a + b;
    }

    @PostMapping(value = "/mock/c")
    public User mockTestC(@RequestBody User user) {
        return user;
    }

    private final String PATH = "F:";

    @PostMapping(value = "/file")
    public String file(MultipartFile file) throws IOException {
        File localFile = new File(PATH, "test.txt");

        file.transferTo(localFile);

        return file.getName();
    }


}
