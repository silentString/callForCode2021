package com.boc.water.common;

import java.io.Serializable;

public class Msg implements Serializable {
    private static final long serialVersionUID = -6439521013888874967L;
    private Object data;
    private String code;
    private String msg;

    public Msg(String code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Msg getMsg(String code, String msg, Object data){
        return new Msg(code, msg, data);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
