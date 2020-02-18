package com.hhi.multipledbsample.service;

import com.hhi.multipledbsample.dto.Third;
import com.hhi.multipledbsample.repository.ThirdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ThirdService {
    private final ThirdRepository thirdRepository;

    Third third = new Third();

    public void insertThird(){
        third.setThird_name("yeonghun");
        third.setThird_number(31);
        thirdRepository.insertThird(third);
        throw new NullPointerException();
    }
}
