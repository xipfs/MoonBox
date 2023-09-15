package net.xipfs.moonbox.common;

/**
 * 返回
 *
 * @author hui.xie
 * @version 1.0
 * @since 2023/09/15/10:58
 */

public class ResponseBean<T> {

    //@ApiModelProperty(value = "状态 0:成功 -1:服务器异常 2:业务异常", name = "status")
    private int status = -1;
    //@ApiModelProperty(value = "信息", name = "message")
    private String message;
    //@ApiModelProperty(value = "数据", name = "data")
    private T data;

    public ResponseBean() {
        status = 0;
    }

    public ResponseBean(T data) {
        status = 0;
        this.data = data;
    }

    public ResponseBean(int code, T data) {
        this.status = code;
        this.data = data;
    }

    public ResponseBean(int resultCode, String resultMsg) {
        this.status = resultCode;
        this.message = resultMsg;
    }

    public ResponseBean(int status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.message = msg;
    }

    public static <T> ResponseBean<T> success() {
        return success(null);
    }

    public static <T> ResponseBean<T> success(T data) {
        return new ResponseBean<>(ResponseCodeEnum.SUCCESS.getCode(), data);
    }

    public static <T> ResponseBean<T> failed() {
        return new ResponseBean<>(ResponseCodeEnum.FAIL.getCode(), ResponseCodeEnum.FAIL.getMsg());
    }

    public static <T> ResponseBean<T> failed(int code, String message) {
        return new ResponseBean<>(code, message);
    }

    public static <T> ResponseBean<T> failed(String msg) {
        return new ResponseBean<>(ResponseCodeEnum.FAIL.getCode(), msg);
    }

    public Boolean isSuccess() {
        return ResponseCodeEnum.SUCCESS.getCode() == this.status;
    }

    public static <T> ResponseBean<T> createByErrorCode(ResponseCodeEnum errorCodeEnum) {
        return new ResponseBean<>(errorCodeEnum.getCode(), errorCodeEnum.getMsg());
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
