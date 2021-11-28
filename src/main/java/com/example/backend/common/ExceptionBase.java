package com.example.backend.common;

/**
 * @author ocean
 * @date 2021/9/30
 */
public class ExceptionBase extends Exception{
    private IErrorCode code;
    private Object[] params;

    public ExceptionBase(ExceptionBase e){
        super(e.getMessage());
        this.code = e.getCode();
        this.params = e.getParams();
    }

    public ExceptionBase(IErrorCode code){
        super(code.getErrMsgPattern());
        this.code = code;
    }

    public ExceptionBase(IErrorCode code, Object... params){
        super(String.format(code.getErrMsgPattern(),params));
        this.code = code;
        this.params = params;
    }

    public IErrorCode getCode() {
        return code;
    }

    public Object[] getParams() {
        return params;
    }
}
