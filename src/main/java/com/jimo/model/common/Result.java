package com.jimo.model.common;

/**
 * @author jimo
 * @func 封装统一的返回数据
 * @date 2018/8/24 22:46
 */
public class Result {
    /**
     * 成功为true
     */
    private boolean ok;
    /**
     * 错误消息或其他提示
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;

    public Result() {
        this(true, "", null);
    }

    public Result(Object data) {
        this(true, "", data);
    }

    public Result(boolean ok, String msg) {
        this(ok, msg, null);
    }

    public Result(boolean ok, String msg, Object data) {
        this.ok = ok;
        this.msg = msg;
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
