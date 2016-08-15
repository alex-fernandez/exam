package com.alexfrndz.pojo.exceptions;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public NotFoundException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
