package team6.car.apartment.response;

import lombok.Data;
import team6.car.apartment.response.StatusEnum;

@Data
public class Message<T> {

    private team6.car.apartment.response.StatusEnum status;
    private String message;
    private T data;

    public Message() {
        this.status = team6.car.apartment.response.StatusEnum.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }

    public void setStatus(team6.car.apartment.response.StatusEnum status) {
        this.status = status;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
