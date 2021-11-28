package com.example.backend.common.ErrorType;

import com.example.backend.common.IErrorCode;

/**
 * @author ocean
 * @date 2021/10/21
 */
public enum LoginErrorCode implements IErrorCode {
    //未登录
    NO_LOGIN(1L,1L,"尚未登录"),
    //账户被锁定
    ACCOUNT_LOCKED(1L,2L,"账户被锁定，请联系管理员!"),
    //密码过期
    CREDENTIALS_EXPIRED(1L,3L,"密码过期，请联系管理员"),
    //账户过期
    ACCOUNT_EXPIRED(1L,4L,"账户过期，请联系管理员!"),
    //账户被禁用
    ACCOUNT_DISABLED(1L,5L,"账户被禁用，请联系管理员!"),
    //用户名或密码输入错误
    BAD_CREDENTIALS(1L,6L,"用户名或者密码输入错误，请重新输入!");
    ;

    private long classCode;
    private long detailCode;
    private String errMsgPattern;

    private LoginErrorCode(long classCode, long detailCode, String errMsgPattern){
        this.classCode = classCode;
        this.detailCode = detailCode;
        this.errMsgPattern = errMsgPattern;
    }

    @Override
    public long getClassCode() {
        return classCode;
    }

    @Override
    public long getDetailCode() {
        return detailCode;
    }

    @Override
    public String getErrMsgPattern() {
        return errMsgPattern;
    }
}
