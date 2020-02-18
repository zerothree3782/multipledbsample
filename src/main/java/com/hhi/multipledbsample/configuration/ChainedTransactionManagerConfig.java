package com.hhi.multipledbsample.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChainedTransactionManagerConfig {

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(PlatformTransactionManager firstTransactionManager, PlatformTransactionManager secondTransactionManager,PlatformTransactionManager thirdTransactionManager){
        return new ChainedTransactionManager(firstTransactionManager,secondTransactionManager, thirdTransactionManager);
    }
}
