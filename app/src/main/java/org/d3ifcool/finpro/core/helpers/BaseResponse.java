package org.d3ifcool.finpro.core.helpers;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("error")
    private final Boolean error = false;

    @SerializedName("success")
    private final Boolean success = false;

    @SerializedName("message")
    private final String message = "";

    public Boolean getError() {
        return error;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
