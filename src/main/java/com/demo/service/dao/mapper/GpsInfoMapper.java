package com.demo.service.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.service.dao.entity.GpsInfo;
import org.apache.ibatis.annotations.Param;

/**
 * gps_info mapper
 *
 * @author shenhongjun
 * @since 2020/4/16
 */
public interface GpsInfoMapper extends BaseMapper<GpsInfo> {
    GpsInfo queryById(@Param("id") Integer id);
}
