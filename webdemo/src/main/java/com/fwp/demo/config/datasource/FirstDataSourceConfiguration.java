package com.fwp.demo.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author fwp
 * @version 1.0.0
 * @Description 数据源配置
 * @createTime 2021/06/16 15:41:00
 */
@Configuration
@MapperScan(value = "com.fwp.demo.mapper" ,sqlSessionTemplateRef = "firstSqlSessionTemplate")
public class FirstDataSourceConfiguration {

    @Bean(name = "firstDataSource")
    @Primary //配置默认数据源
    @ConfigurationProperties(prefix = "spring.datasource.first")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "firstSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("firstDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        Interceptor[] interceptor = new Interceptor[]{new PageHelper()};
        bean.setPlugins(interceptor);

        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath*:cn/com/htsc/base/mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "firstTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("firstDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name="firstSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("firstSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
