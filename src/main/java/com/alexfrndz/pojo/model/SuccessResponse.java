package com.alexfrndz.pojo.model;


public class SuccessResponse<IdType> {


    private IdType id = null;

    public SuccessResponse() {
    }

    public SuccessResponse(IdType id) {
        this.id = id;
    }


    public IdType getId() {
        return id;
    }

    public void setId(IdType id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SuccessResponse {\n");
        sb.append("  id: ").append(id).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}