package org.xi.common.constant;

/**
 * 
 * All Rights Reserved.
 * @version 1.0 2018/05/08 13:15 郗世豪（xish@cloud-young.com）
 */
public enum OperationConstants {

    SYSTEM_ERROR(100000, "系统错误"),
    VALIDATION_FAILED(100001, "验证不通过"),
    SERVICE_NOT_AVAILABLE(100100, "服务提供方不存在");

    private String message;
    private int code;

    OperationConstants(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "OperationConstants{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
