package org.xi.multidatasourse.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.xi.multidatasourse.mapper", sqlSessionTemplateRef = "quick1SqlSessionTemplate")
public class Quick1DatabaseConfig {

    @Bean(name = "quick1Data")
    @ConfigurationProperties(prefix = "quick1.datasource")
    public DataSource quick1Data() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "quick1SqlSessionFactory")
    public SqlSessionFactory quick1SqlSessionFactory(@Qualifier("quick1Data") DataSource quick1Data) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(quick1Data);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "quick1TransactionManager")
    public DataSourceTransactionManager quick1TransactionManager(@Qualifier("quick1Data") DataSource quick1Data) {
        return new DataSourceTransactionManager(quick1Data);
    }

    @Bean(name = "quick1SqlSessionTemplate")
    public SqlSessionTemplate quick1SqlSessionTemplate(@Qualifier("quick1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
