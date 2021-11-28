package com.example.backend.common;

/**
 * @author ocean
 * @date 2021/9/30
 */
public class ReturnCodeProvider {
    public <T> RCode<T> getRCode() {
        return new RCode();
    }
    public <T> RCode<T> getRCode(T data){
        return new RCode(data);
    }
    public <T> RCode<T> getRCode(IErrorCode error){
        return new RCode(error, (Object)null);
    }
    public <T> RCode<T> getRCode(IErrorCode error, T data){
        return new RCode(error, data);
    }

    public <T> RCode<T> getRCode(IErrorCode error, Object... params){
        RCode<T> r = new RCode(error, (Object)null);
        r.setPatternParam(params);
        return r;
    }

    public <T> RCode<T> getRCode(IErrorCode error, T data, Object... params){
        RCode<T> r = new RCode(error, data);
        r.setPatternParam(params);
        return r;
    }

    public <T> RCode<T> getRCode(ExceptionBase eb){
        return eb.getParams() !=null?this.getRCode(eb.getCode(), eb.getParams()):this.getRCode(eb.getCode());
    }

    public <T> RCode<T> getRCode(ExceptionBase eb, T data){
        return eb.getParams() !=null?this.getRCode(eb.getCode(), data, eb.getParams()):this.getRCode(eb.getCode(), data);
    }

}
