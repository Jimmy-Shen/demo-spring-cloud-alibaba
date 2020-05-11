package com.demo.service.pojo.vo;


import com.demo.service.pojo.BaseVo;
import lombok.Data;

/**
 * gps_info vo
 *
 * @author shenhongjun
 * @since 2020/4/16
 */
@Data
public class GpsInfoVo extends BaseVo {
    /**
     * 主键
     */
    private String id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 车牌号
     */
    private String carNum;

    /**
     * 地址
     */
    private String addr;

    /**
     * 经度(百度坐标)
     */
    private String x;

    /**
     * 纬度(百度坐标)
     */
    private String y;
}
