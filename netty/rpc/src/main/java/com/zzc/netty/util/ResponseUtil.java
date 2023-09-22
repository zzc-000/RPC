package com.zzc.netty.util;

public class ResponseUtil {
    public static Response createSuccessResults(){
        return new Response();
    }
    public static Response createFailResults(String code,String msg){
        Response response = new Response();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }
    public static Response createSuccessResults(Object content){
        Response response = new Response();
        response.setResult(content);
        return response;
    }
}
