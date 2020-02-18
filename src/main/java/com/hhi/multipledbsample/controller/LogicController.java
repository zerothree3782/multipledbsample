package com.hhi.multipledbsample.controller;

import com.hhi.multipledbsample.service.LogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/logic")
public class LogicController {
    private final LogicService logicService;

    @GetMapping
    public void insertAll(){
        logicService.insertAll();
    }
}
