package com.hhi.multipledbsample.configuration;

import com.hhi.multipledbsample.configuration.custommapper.SecondMapper;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@Slf4j
@EnableTransactionManagement
@MapperScan(basePackages = "com.hhi.multipledbsample",annotationClass = SecondMapper.class,sqlSessionFactoryRef = "secondSqlSessionFactory")
public class SecondDatasourceConfig {
    @Value("${myconfig.dbbaseMapperLocation}")
    private String dbbaseMapperLocations;

    @Bean(name="secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari2")
    public DataSource secondDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "proxySecondDataSource")
    public DataSource proxySecondDataSource(DataSource secondDataSource){
        return new LazyConnectionDataSourceProxy(secondDataSource);
    }

    @Bean(name="secondTransactionManager")
    public PlatformTransactionManager secondTransactionManager(DataSource proxySecondDataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(proxySecondDataSource);
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }

    @Bean(name="secondSqlSessionFactory")
    public SqlSessionFactory secondSqlSessionFactory(DataSource proxySecondDataSource, ApplicationContext applicationContext) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(proxySecondDataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(dbbaseMapperLocations));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name="secondsqlSessionTemplate")
    public SqlSessionTemplate seconddbSqlsessionTemplate(SqlSessionFactory secondSqlSessionFactory){
        return new SqlSessionTemplate(secondSqlSessionFactory);
    }
}
