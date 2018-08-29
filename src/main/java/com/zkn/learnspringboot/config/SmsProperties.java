package com.zkn.learnspringboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/** 
 * 类说明：短信发送属性
 * 文件名称：SmsProperties.java
 * @author 作者:
 * @date 创建时间：2017年12月22日 下午5:03:47
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix = "sms")
@Data
public class SmsProperties {
	/**
     * 默认短信运营商：0联通，1梦网，2杭州睿动，其他默认的
     */
	public final static int DEFAULT_SMS_SEND_PLATFORM = 1;
	
	/**
     * 短信运营商
     */
	private int smsSendPlatform = DEFAULT_SMS_SEND_PLATFORM;
	
	//移动梦网短信发送相关参数
	private String sendUrlMw;
	private String userIdMw;
	private String passwordMw;
	
	// 短信验证码开始部分/结尾部分
	private String identifyMsgBegin = "您的验证码是";
	private String identifyMsgEnd = "，30分钟内有效，请勿将验证码提供给他人，若非本人操作可忽略。";
	// 服务商初次开通
	private String supplierReqBegin = "尊敬的服务商，您已成功开通诺诺众包平台服务，登录账号密码跟您的";
	private String supplierReqEnd = "账号密码相同。";
	// 审核通过
	private String verified = "尊敬的服务商，您修改过的信息已审核通过，请及时登录处理任务。如有疑问，请致电95113。";
}
