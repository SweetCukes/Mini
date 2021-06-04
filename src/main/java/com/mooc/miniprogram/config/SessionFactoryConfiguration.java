package com.mooc.miniprogram.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author SweetC
 * @Date 2021/6/4
 */

@Configuration
public class SessionFactoryConfiguration {
    //mybatis_config.xml 配置文件路径
    @Value("${mybatis_config_file}")
    private String mybatisConfigFilePath;

    //mybatis_mapper文件路径
    @Value("${mapper_path}")
    private String mapperPath;

    //实体类所在的packege
    @Value("${entity_packege}")
    private String entityPackege;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Bean(name = "sqlSesstionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //扫描mybatis-config.xml文件
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFilePath));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packegSearchPath = PathMatchingResourcePatternResolver.CLASSPATH_URL_PREFIX + mapperPath;
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packegSearchPath));
        sqlSessionFactoryBean.setDataSource(dataSource);

        sqlSessionFactoryBean.setTypeAliasesPackage(entityPackege);
        return sqlSessionFactoryBean;
    }
}
