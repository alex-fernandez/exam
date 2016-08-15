package com.alexfrndz.pojo.model;


public class SuccessMessageResponse<IdType> extends SuccessResponse {

    public SuccessMessageResponse() {
    }


    /**
     * status code of response
     */
    private String status = null;

    /**
     * response message
     */

    private String message = null;

    public SuccessMessageResponse(IdType id, String status, String message) {
        super(id);
        this.status = status;
        this.message = message;
    }

    public SuccessMessageResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }




    /**
     * status code of response*
     */
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * response message*
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SuccessMessageResponse {\n");
        sb.append("  id: ").append(getId()).append("\n");
        sb.append("  status: ").append(getStatus()).append("\n");
        sb.append("  message: ").append(getMessage()).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}