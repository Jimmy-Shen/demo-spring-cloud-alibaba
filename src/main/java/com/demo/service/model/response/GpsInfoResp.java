package com.demo.service.model.response;

import com.demo.service.model.BaseResp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * gps_info response
 *
 * @author shenhongjun
 * @since 2020/4/17
 */
@ApiModel(description = "经纬度返回信息")
@Data
public class GpsInfoResp extends BaseResp {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键,数据类型为Long或者Integer,例如405562")
    private String id;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String mobile;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String carNum;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String addr;

    /**
     * 经度(百度坐标)
     */
    @ApiModelProperty("经度(百度坐标)")
    private String x;

    /**
     * 纬度(百度坐标)
     */
    @ApiModelProperty("纬度(百度坐标)")
    private String y;
}
