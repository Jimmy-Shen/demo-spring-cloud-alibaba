package com.demo.service.model.custom;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 自定义
 *
 * @author shenhongjun
 * @since 2020/4/27
 */
public class CustomBaseResponse<T> implements Serializable {

    /**
     * 标识
     *
     *     1-数据成功，
     *     0-没有数据，
     *     -1-获取异常
     */
    @ApiModelProperty(value = "返回code 1-数据成功，0-没有数据，-1-获取异常")
    private Integer code;

    /**
     * 返回信息
     */
    @ApiModelProperty(value = "返回信息")
    private String message;

    /**
     * 数据集合
     */
    @ApiModelProperty(value = "返回实体数据")
    private T data;

    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private Long stamp;

    public CustomBaseResponse() {
    }

    public CustomBaseResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.stamp = System.currentTimeMillis();
    }

    public CustomBaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.stamp = System.currentTimeMillis();
    }

    public static <T> CustomBaseResponse<T> success(T data) {
        return new CustomBaseResponse(ResponseCode.SUCCESS.getValue(), ResponseCode.SUCCESS.getMsg(), data);
    }

    public static <T> CustomBaseResponse<T> success() {
        return new CustomBaseResponse(ResponseCode.SUCCESS.getValue(), ResponseCode.SUCCESS.getMsg());
    }

    public static <T> CustomBaseResponse<T> noData() {
        return new CustomBaseResponse(ResponseCode.NO_DATA.getValue(), ResponseCode.NO_DATA.getMsg());
    }

    public static <T> CustomBaseResponse<T> error() {
        return new CustomBaseResponse(ResponseCode.EXCEPTION.getValue(), ResponseCode.EXCEPTION.getMsg());
    }

    public static <T> CustomBaseResponse<T> error(String msg) {
        return new CustomBaseResponse(ResponseCode.EXCEPTION.getValue(), msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getStamp() {
        return stamp;
    }

    public void setStamp(Long stamp) {
        this.stamp = stamp;
    }

}