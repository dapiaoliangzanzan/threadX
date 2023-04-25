package com.threadx.metrics.server.common.code;

/**
 * @author huangfu
 * @date 默认的错误信息
 */
public class DefaultExceptionCode implements IExceptionCode {

    private final String message;

    public DefaultExceptionCode(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return "default-0";
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
