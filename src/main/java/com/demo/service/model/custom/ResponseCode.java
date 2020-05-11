package com.demo.service.model.custom;

/**
 * 自定义端响应code
 *
 * @author shenhongjun
 * @since 2020/4/27
 */
public enum ResponseCode {

    SUCCESS(1, "成功"),
    NO_DATA(0, "查询不到数据"),
    EXCEPTION(-1, "异常");

    private int value;
    private String msg;

    ResponseCode(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }


    public static ResponseCode get(int value) {
        for (ResponseCode webResponseCode : ResponseCode.values()) {
            if (webResponseCode.value == value){
                return webResponseCode;
            }
        }
        return null;
    }

    public int getValue() {
        return this.value;
    }

    public String getMsg() {
        return msg;
    }
}
