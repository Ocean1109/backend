package com.example.backend.common;

/**
 * @author ocean
 * @date 2021/9/30
 */
public interface IErrorCode {
    /**
     * 返回错误所属的大类
     *
     * @return long
     */
    long getClassCode();
    /**
     * 返回错误所属的具体类型
     *
     * @return long
     */
    long getDetailCode();
    /**
     * 返回错误的信息模版
     *
     * @return String
     */
    String getErrMsgPattern();
}
