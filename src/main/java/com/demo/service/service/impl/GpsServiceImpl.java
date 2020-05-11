package com.demo.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.service.dao.entity.GpsInfo;
import com.demo.service.dao.mapper.GpsInfoMapper;
import com.demo.service.service.GpsService;
import org.springframework.stereotype.Service;

/**
 * gps service impl
 *
 * @author shenhongjun
 * @since 2020/4/16
 */
@Service
public class GpsServiceImpl extends ServiceImpl<GpsInfoMapper, GpsInfo> implements GpsService {
    @Override
    public GpsInfo queryById(Integer id) {
        return super.baseMapper.queryById(id);
    }
}
