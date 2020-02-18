package com.hhi.multipledbsample.service;

import com.hhi.multipledbsample.dto.First;
import com.hhi.multipledbsample.repository.FirstRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class FirstService {
    private final FirstRepository firstRepository;

    public void insertMaster(){
        First first = new First();
        first.setFirst_name("yeonghun");
        first.setFirst_number(31);
        firstRepository.insertFirst(first);
    }

    public List<First> selectAll(){
        return firstRepository.selectAll();
    }
}
