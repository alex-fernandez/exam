package com.alexfrndz.pojo.exceptions;

public class Error {

    /**
     * Error code
     */
    private String code = null;

    /**
     * Error message
     */
    private String message = null;

    public Error(String message) {
        this.message = message;
    }

    public Error(String code, String message){
        this.code = code;
        this.message = message;
    }
    /**
     * Error code*
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Error message*
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Error {\n");

        sb.append("  code: ").append(code).append("\n");
        sb.append("  message: ").append(message).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
