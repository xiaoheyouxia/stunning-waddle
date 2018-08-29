package com.zkn.learnspringboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/** 
 * 类说明：Mybatis数据源
 * 文件名称：DataSourceConfig.java
 * @author 作者:
 * @date 创建时间：2017年9月11日 下午2:23:49
 * @version 1.0
 */
@Configuration
@ConditionalOnClass(com.alibaba.druid.pool.DruidDataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
@MapperScan(basePackages = "com.zkn.learnspringboot.mapper")
public class DataSourceConfig {
	private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
	
    @Autowired
    private DruidDataSourceProperty druidDataSourceProperty;
	
    /**
     * 数据源(用户数据)
     */
    @Bean     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource userDataSource() {
    	DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(druidDataSourceProperty.getUrl());
        datasource.setUsername(druidDataSourceProperty.getUsername());
        datasource.setPassword(druidDataSourceProperty.getPassword());
        datasource.setDriverClassName(druidDataSourceProperty.getDriverClassName());

        datasource.setInitialSize(druidDataSourceProperty.getInitialSize());
        datasource.setMinIdle(druidDataSourceProperty.getMinIdle());
        datasource.setMaxActive(druidDataSourceProperty.getMaxActive());
        // 配置获取连接等待超时的时间
        datasource.setMaxWait(druidDataSourceProperty.getMaxWait());
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        datasource.setTimeBetweenEvictionRunsMillis(druidDataSourceProperty.getTimeBetweenEvictionRunsMillis());
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        datasource.setMinEvictableIdleTimeMillis(druidDataSourceProperty.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(druidDataSourceProperty.getValidationQuery());
        datasource.setTestOnBorrow(druidDataSourceProperty.isTestOnBorrow());
        datasource.setPoolPreparedStatements(druidDataSourceProperty.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(druidDataSourceProperty.getMaxPoolPreparedStatementPerConnectionSize());
        datasource.setUseGlobalDataSourceStat(druidDataSourceProperty.isUseGlobalDataSourceStat());
        try {
            datasource.setFilters(druidDataSourceProperty.getFilters());
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(druidDataSourceProperty.getConnectionProperties());

        return datasource;
    }
}
