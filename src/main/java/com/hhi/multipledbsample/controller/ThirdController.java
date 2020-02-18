package com.hhi.multipledbsample.controller;

import com.hhi.multipledbsample.service.ThirdService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/third")
@RequiredArgsConstructor
public class ThirdController {
    private final ThirdService thirdService;

    @GetMapping
    public void insertThird(){
        thirdService.insertThird();
    }
}
