package team6.car.apartment.response;

import lombok.Data;
import team6.car.member.response.StatusEnum;

@Data
public class Message {

    private StatusEnum status;
    private String message;
    private Object data;

    public Message() {
        this.status = StatusEnum.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }
}