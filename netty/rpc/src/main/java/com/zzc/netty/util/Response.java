package com.zzc.netty.util;

public class Response {
    private Long id;
    private Object result;
    private String code="00000"; //00000表示成功
    private String msg; //失败原因

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public Object getResult() {
        return result;
    }


}
