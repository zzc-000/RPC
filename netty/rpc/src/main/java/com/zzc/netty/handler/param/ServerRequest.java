package com.zzc.netty.handler.param;

public class ServerRequest {
    private Long id;
    private Object content;
    private String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Long getId() {
        return id;
    }

    public Object getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
