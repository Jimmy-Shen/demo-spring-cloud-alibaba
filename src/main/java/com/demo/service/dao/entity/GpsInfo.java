package com.demo.service.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * gps_info
 *
 * @author shenhongjun
 * @since 2020/4/16
 */
@Data
public class GpsInfo {
    /**
     * 主键
     */
    private Long id;

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

    /**
     * 经度(火星坐标)
     */
    private String gX;

    /**
     * 纬度(火星坐标)
     */
    private String gY;

    /**
     * 经度(各来源返回的原始坐标
     */
    private String sX;

    /**
     * 纬度(各来源返回的原始坐标
     */
    private String sY;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 市
     */
    private String city;

    /**
     * 对应t_car
     */
    private Long carId;

    /**
     * 订单号
     */
    private String orderNum;

    /**
     * 定位数据来源：1-app，2-LBS，3-中交兴路
     */
    private Short source;
}
