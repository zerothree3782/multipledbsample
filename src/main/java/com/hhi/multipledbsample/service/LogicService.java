package com.hhi.multipledbsample.service;

import com.hhi.multipledbsample.dto.First;
import com.hhi.multipledbsample.dto.Second;
import com.hhi.multipledbsample.dto.Third;
import com.hhi.multipledbsample.repository.FirstRepository;
import com.hhi.multipledbsample.repository.SecondRepository;
import com.hhi.multipledbsample.repository.ThirdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LogicService {

    private final FirstRepository firstRepository;
    private final SecondRepository secondRepository;
    private final ThirdRepository thirdRepository;
    First first = new First();
    Second second = new Second();
    Third third = new Third();

    public void insertAll(){
        first.setFirst_name("yeonghun");
        first.setFirst_number(31);
        second.setSecond_name("yeonghun");
        second.setSecond_number(31);
        third.setThird_name("yeonghun");
        third.setThird_number(31);
        firstRepository.insertFirst(first);
        secondRepository.insertSecond(second);
        thirdRepository.insertThird(third);
    }
}
