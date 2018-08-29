package com.zkn.learnspringboot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidDataSourceProperty {
	// 数据库连接地址
	private String url;
	// 用户名
	private String username;
	// 密码
	private String password;
	// 数据库驱动
	private String driverClassName;
	// 初始化大小，最小，最大
	private int initialSize = 30;
	private int minIdle = 30;
	private int maxActive = 100;
	// 配置获取连接等待超时的时间
	private int maxWait;
	// 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
	private int timeBetweenEvictionRunsMillis = 1000 * 60;
	// 配置一个连接在池中最小生存的时间，单位是毫秒
	private int minEvictableIdleTimeMillis = 1000 * 60 * 5;
	// 检测连接是否有效的sql
	private String validationQuery;
	private boolean testOnBorrow = true;

	// PSCache Mysql下建议关闭
	private boolean poolPreparedStatements = false;

	private int maxPoolPreparedStatementPerConnectionSize = -1;
	
	// 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'config'用于配置加密
	private String filters;

	// 合并多个DruidDataSource的监控数据
	private boolean useGlobalDataSourceStat = false;

	private String connectionProperties;
}
