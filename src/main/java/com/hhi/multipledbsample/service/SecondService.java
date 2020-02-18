package com.hhi.multipledbsample.service;

import com.hhi.multipledbsample.dto.Second;
import com.hhi.multipledbsample.repository.SecondRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SecondService {
    private final SecondRepository secondRepository;

    Second second = new Second();

    public void insertSecond(){
        second.setSecond_name("yeonghun");
        second.setSecond_number(31);
        secondRepository.insertSecond(second);
    }
}
