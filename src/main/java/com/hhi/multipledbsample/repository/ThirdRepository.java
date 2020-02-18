package com.hhi.multipledbsample.repository;

import com.hhi.multipledbsample.configuration.custommapper.ThirdMapper;
import com.hhi.multipledbsample.dto.Third;
import org.springframework.stereotype.Repository;

@Repository
@ThirdMapper
public interface ThirdRepository {
    public void insertThird(Third third);
}
