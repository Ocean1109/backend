package com.example.backend.common;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * @author ocean
 * @date 2021/9/30
 */
public class RCode<T> implements Serializable {
    private Long errCode;
    private String errMsg;
    private T data;
    private transient String errMsgPattern;

    public RCode(T data) {
        this.errCode = 0L;
        this.errMsg = "Success";
        this.errMsgPattern = "Success";
        this.data = data;
    }

    public RCode(IErrorCode code, T data){
        long classCode = code.getClassCode() % 10L;
        long detailCode = code.getDetailCode() % 10000L;
        this.errCode = classCode * 10000L + detailCode;
        this.errMsgPattern = code.getErrMsgPattern();
        this.errMsg = this.errMsgPattern;
        this.data = data;
    }
    public RCode() {
        this(null);
    }


    public void setPatternParam(Object... params){
        this.errMsg = String.format(this.errMsgPattern,params);
    }

    public Long getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
