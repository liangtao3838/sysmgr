package com.sys.mgr.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by liangtao on 2018/3/16.
 */
public final class JsonResponse<T> {

    /**
     * 未知错误
     */
    public static final int CODE_UNKNOWN_ERROR = 0;
    /**
     * 成功
     */
    private static final int CODE_SUCCESS = 1;
    /**
     * 无权操作
     */
    private static final int CODE_NO_AUTH = 2;

    private int code;
    private String msg;
    private T result;

    /**
     * 默认构造函数
     */
    private JsonResponse() {
    }

    /**
     * 构造成功响应
     *
     * @param t 操作结果
     */
    public JsonResponse(T t) {
        code = CODE_SUCCESS;
        result = t;
    }

    /**
     * 构造成功响应
     *
     * @param t   操作结果
     * @param msg 成功信息
     */
    public JsonResponse(T t, String msg) {
        this(t);
        this.msg = msg;
    }

    /**
     * 构造无权限响应
     *
     * @return 无权限响应
     */
    public static JsonResponse noAuthResponse() {
        JsonResponse resp = new JsonResponse();
        resp.code = CODE_NO_AUTH;
        resp.msg = "您没有权限执行此操作，请退出";
        return resp;
    }

    /**
     * 未知错误响应
     *
     * @return 未知错误响应
     */
    public static JsonResponse unknownErrorResponse() {
        return errorResponse(CODE_UNKNOWN_ERROR, "未知错误");
    }

    /**
     * 构造错误响应
     *
     * @param code 错误代码
     * @param msg  错误信息
     * @return 错误响应
     */
    public static JsonResponse errorResponse(int code, String msg) {
        JsonResponse resp = new JsonResponse();
        resp.code = code;
        resp.msg = msg;
        return resp;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String toJSON(){
        return JSONObject.toJSONString(this);
    }

}
