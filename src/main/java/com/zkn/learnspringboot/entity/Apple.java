package com.zkn.learnspringboot.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.*;

import java.util.Date;

/**
 * 
 * 类说明:财务结算信息
 * 文件名称：ServiceInfo.java
 * @author 
 * @date 2017年12月11日 下午3:43:01
 * @version 1.0
 */
@lombok.Getter
@lombok.Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Apple {
	private String color;
	private Integer weight;
	
}
