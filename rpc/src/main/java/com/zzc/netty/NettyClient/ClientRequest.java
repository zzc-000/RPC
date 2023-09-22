package com.zzc.netty.NettyClient;

import java.util.concurrent.atomic.AtomicLong;

public class ClientRequest {
    private final long id;
    private Object content;
    private String command;
    private final AtomicLong aid=new AtomicLong(1);
    public  ClientRequest(){
        id = aid.incrementAndGet();
    }
    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}


