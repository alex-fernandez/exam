package com.alexfrndz.pojo.exceptions;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponse {

    private List<Error> errors = new ArrayList<Error>();

    public ErrorResponse() {
    }

    public ErrorResponse(List<Error> errors) {
        this.errors = errors;
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ErrorResponse {\n");

        sb.append("  errors: ").append(errors).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
