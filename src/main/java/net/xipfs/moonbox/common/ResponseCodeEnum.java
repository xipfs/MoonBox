package net.xipfs.moonbox.common;

/**
 * description
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/15/11:10
 */

public enum ResponseCodeEnum {
    SUCCESS(0, "成功"),
    SERVER_EXCEPTION(-1, "服务器异常"),
    ILLEGAL_ARGS(-400, "参数不合法"),
    FAIL(-2, "操作失败"),
    ;
    private int code;
    private String msg;
    ResponseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
