package org.xi.multidatasourse.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.xi.multidatasourse.mapper2", sqlSessionTemplateRef = "quick2SqlSessionTemplate")
public class Quick2DatabaseConfig {

    @Bean(name = "quick2Data")
    @ConfigurationProperties(prefix = "quick2.datasource")
    @Primary
    public DataSource quick2Data() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "quick2SqlSessionFactory")
    @Primary
    public SqlSessionFactory quick2SqlSessionFactory(@Qualifier("quick2Data") DataSource quick2Data) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(quick2Data);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper2/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "quick2TransactionManager")
    @Primary
    public DataSourceTransactionManager quick2TransactionManager(@Qualifier("quick2Data") DataSource quick2Data) {
        return new DataSourceTransactionManager(quick2Data);
    }

    @Bean(name = "quick2SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate quick2SqlSessionTemplate(@Qualifier("quick2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
