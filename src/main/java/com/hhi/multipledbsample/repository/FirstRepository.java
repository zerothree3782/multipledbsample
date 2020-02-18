package com.hhi.multipledbsample.repository;

import com.hhi.multipledbsample.configuration.custommapper.FirstMapper;
import com.hhi.multipledbsample.dto.First;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@FirstMapper
public interface FirstRepository {
    void insertFirst(First first);

    List<First> selectAll();
}
