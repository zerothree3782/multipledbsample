package com.hhi.multipledbsample.configuration;

import com.hhi.multipledbsample.configuration.custommapper.FirstMapper;
import com.zaxxer.hikari.HikariDataSource;
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
@EnableTransactionManagement
@MapperScan(basePackages = "com.hhi.multipledbsample", annotationClass = FirstMapper.class,sqlSessionFactoryRef = "firstSqlSessionFactory")
public class FirstDatasourceConfig {
    @Value("${myconfig.dbbaseMapperLocation}")
    private String dbbaseMapperLocations;

    @Bean(name = "firstDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari1")
    public DataSource firstDataSource(){

        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "proxyFirstDataSource")
    public DataSource proxyFirstDataSource(DataSource firstDataSource){
        return new LazyConnectionDataSourceProxy(firstDataSource);
    }

    @Bean(name = "firstTransactionManager")
    public PlatformTransactionManager firstTransactionManager(DataSource proxyFirstDataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(proxyFirstDataSource);
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }

    @Bean(name = "firstSqlSessionFactory")
    public SqlSessionFactory firstSqlSessionFactory(DataSource proxyFirstDataSource, ApplicationContext applicationContext) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(proxyFirstDataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(dbbaseMapperLocations));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "firstSqlSessionTemplate")
    public SqlSessionTemplate firstSqlSessionTemplate(SqlSessionFactory firstSqlSessionFactory){
        return new SqlSessionTemplate(firstSqlSessionFactory);
    }
}
