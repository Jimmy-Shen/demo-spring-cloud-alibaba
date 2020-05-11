package com.demo.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.service.dao.entity.GpsInfo;

/**
 * gps service
 *
 * @author shenhongjun
 * @since 2020/4/16
 */
public interface GpsService extends IService<GpsInfo> {
    /**
     * 根据id获取对应的GPS信息
     * @param id
     * @return
     */
    GpsInfo queryById(Integer id);
}
