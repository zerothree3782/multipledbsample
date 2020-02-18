package com.hhi.multipledbsample.controller;

import com.hhi.multipledbsample.service.FirstService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/first")
public class FirstController {

    private final FirstService firstService;
    @GetMapping
    public int insertMaster(){
            firstService.insertMaster();
            return firstService.selectAll().size();
    }

}
