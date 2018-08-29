package com.zkn.learnspringboot.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 测试配置文件的controller
 * @author: lxh
 * @create: 2018-03-19 15:51
 **/
@RestController
@RequestMapping("/index")
public class IndexController {
    @ApiOperation(value = "配置swagger示例")
    @GetMapping(value="/get")
    public Map<String,Object> getMap(@ApiParam(value = "swagger参数示例") @RequestParam String name){
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("name",name);
        map.put("name",name);
        map.put("name",name);
        map.put("name",name);
        map.put("name",name);
        return map;
    }
}
