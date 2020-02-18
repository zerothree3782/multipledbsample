package com.hhi.multipledbsample.controller;

import com.hhi.multipledbsample.service.SecondService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second")
@RequiredArgsConstructor
public class SecondController {
    private final SecondService secondService;

    @GetMapping
    public void insertSecond(){
        secondService.insertSecond();
    }
}
