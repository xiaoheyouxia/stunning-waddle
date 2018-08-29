package com.zkn.learnspringboot.service.impl;

import com.zkn.learnspringboot.mapper.SecKillMapper;
import com.zkn.learnspringboot.service.ProfileService;
import com.zkn.learnspringboot.service.SecKillService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 秒杀模拟serviceImpl
 * @author: lxh
 * @create: 2018-04-02 17:02
 **/
@Service("secKillService")
public class SecKillServiceImpl implements SecKillService {
    @Autowired
    private SecKillMapper secKillMapper;

    @Override
    public String update() {
        Integer update = secKillMapper.update();
        return update+"";
    }
}
