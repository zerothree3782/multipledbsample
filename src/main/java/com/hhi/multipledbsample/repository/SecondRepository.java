package com.hhi.multipledbsample.repository;

import com.hhi.multipledbsample.configuration.custommapper.SecondMapper;
import com.hhi.multipledbsample.dto.Second;
import org.springframework.stereotype.Repository;

@Repository
@SecondMapper
public interface SecondRepository {


    public void insertSecond(Second second);

}
