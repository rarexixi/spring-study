package org.xi.common.model;

import org.xi.common.constant.OperationConstants;

import java.io.Serializable;

/**
 * 
 * All Rights Reserved.
 * @version 1.0 2018/05/08 13:15 郗世豪（xish@cloud-young.com）
 */
public class ResultVo<T> implements Serializable {

    public ResultVo() {

    }

    public ResultVo(T result) {
        this(true, 0, null, result);
    }

    public ResultVo(OperationConstants constants) {
        this(false, constants.getCode(), constants.getMessage(), null);
    }

    public ResultVo(OperationConstants constants, Object data) {
        this(false, constants.getCode(), constants.getMessage(), null, data);
    }

    public ResultVo(int code, String message) {
        this(false, code, message, null);
    }

    public ResultVo(boolean success, int code, String message, T result) {
        this(success, code, message, result, null);
    }

    public ResultVo(boolean success, int code, String message, T result, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.result = result;
        this.data = data;
    }

    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 编码
     */
    private int code;
    /**
     * 信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T result;
    /**
     * 附加信息
     */
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
