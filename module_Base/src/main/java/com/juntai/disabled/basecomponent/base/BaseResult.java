package com.juntai.disabled.basecomponent.base;

import java.util.ArrayList;
import java.util.List;

public class BaseResult  {


    public int status;
    public String message;
    public String error;
    public String msg;
    public int code;
    public String type;
    public boolean success;
    public List<String> url;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message == null ? "" : message;
    }

    public String getError() {
        return error == null ? "" : error;
    }

    public void setError(String error) {
        this.error = error == null ? "" : error;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? "" : msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type == null ? "" : type;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getUrl() {
        if (url == null) {
            return new ArrayList<>();
        }
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", error='" + error + '\'' +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                ", type='" + type + '\'' +
                ", success=" + success +
                '}';
    }
}
