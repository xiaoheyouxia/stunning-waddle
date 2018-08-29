package com.zkn.learnspringboot.web.controller;

import com.zkn.learnspringboot.domain.RestResponse;
import com.zkn.learnspringboot.service.SecKillService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 模拟秒杀的controller
 * @author: lxh
 * @create: 2018-03-19 15:51
 **/
@RestController
@RequestMapping("/secKill")
public class SecKillController {
    @Autowired
    private SecKillService secKillService;

    @ApiOperation(value = "模拟秒杀的接口")
    @PostMapping(value="/backGroud")
    public RestResponse update(@ApiParam(value = "swagger参数示例") @RequestParam(required = false) String name){
        if(secKillService.update().equals("1")){
            return RestResponse.success("秒杀成功!");
        } else{
            return RestResponse.success("秒杀失败!");
        }
    }
}
