package com.hhi.multipledbsample.configuration;

import com.hhi.multipledbsample.configuration.custommapper.ThirdMapper;
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
@MapperScan(basePackages = "com.hhi.multipledbsample",annotationClass = ThirdMapper.class,sqlSessionFactoryRef = "thirdSqlSessionFactory")
public class ThirdDatasourceConfig {
    @Value("${myconfig.dbbaseMapperLocation}")
    private String dbbaseMapperLocations;

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari3")
    public DataSource dataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name="proxyThirdDataSource")
    public DataSource proxyThirdDataSource(DataSource dataSource){
        return new LazyConnectionDataSourceProxy(dataSource);
    }

    @Bean(name="thirdTransactionManager")
    public PlatformTransactionManager thirdTransactionManager(DataSource proxyThirdDataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(proxyThirdDataSource);
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }

    @Bean(name="thirdSqlSessionFactory")
    public SqlSessionFactory thirdSqlSessionFactory(DataSource proxyThirdDataSource, ApplicationContext applicationContext) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(proxyThirdDataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(dbbaseMapperLocations));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name="thirdSqlSessionTemplate")
    public SqlSessionTemplate thirdSqlSessionTemplate(SqlSessionFactory thirdSqlSessionFactory){
        return new SqlSessionTemplate(thirdSqlSessionFactory);
    }
}
