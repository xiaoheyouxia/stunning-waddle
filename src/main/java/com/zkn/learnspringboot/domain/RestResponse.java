package com.zkn.learnspringboot.domain;

import lombok.Getter;

import java.util.HashMap;

/**
 * API统一返回对象
 */
@Getter
public class RestResponse {

    /**
     * 状态码
     */
    private int status;

    /**
     * 状态信息
     */
    private String message;

    /**
     * 异常ID
     */
    private String eid = "";

    /**
     * 数据
     */
    private HashMap<String, Object> data;

    private RestResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private RestResponse(int status, String message, String eid) {
        this.status = status;
        this.message = message;
        this.eid = eid;
    }

    private RestResponse(int status, String message, HashMap<String, Object> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static RestResponse success() {
        return new RestResponse(ResponseStatus.OK, ResponseMessage.OK);
    }

    public static RestResponse success(String message) {
        return new RestResponse(ResponseStatus.OK, message);
    }

    public static RestResponse success(HashMap<String, Object> data) {
        return new RestResponse(ResponseStatus.OK, ResponseMessage.OK, data);
    }

    public static RestResponse success(String message, HashMap<String, Object> data) {
        return new RestResponse(ResponseStatus.OK, message, data);
    }

    public static RestResponse error(int status, String message) {
        return new RestResponse(status, message);
    }

    public static RestResponse error(int status, String message, String eid) {
        return new RestResponse(status, message, eid);
    }
}
