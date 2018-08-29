package com.zkn.learnspringboot.domain;

/**
 * 状态码定义
 */
public class ResponseStatus {

    /**
     * 正确
     */
    public static final int OK = 200;

    /**
     * 参数错误
     */
    public static final int PARAM_ERR = 400;

    /**
     * 未授权
     */
    public static final int UN_AUTHORIZED = 401;

    /**
     * 未发现
     */
    public static final int NOT_FOUND = 404;

    /**
     * 请求方法未允许
     */
    public static final int METHOD_NOT_ALLOWED = 405;
    
    /**
     * 文件上传大小超过限制【2M】
     */
    public static final int FILE_SIZE_LIMIT_EXCEEDED = 406;
    
    /**
     * 图片格式必须为：jpg、jpeg、bmp、png中的一种
     */
    public static final int IMG_PATTERN_NOT_ALLOWED = 407;
    
    /**
     * 服务端内部错误
     */
    public static final int INTERNAL_ERR = 500;
    
    /**
     * 图片上传失败
     */
    public static final int IMG_UPLOAD_FAILURE = 510;
}
