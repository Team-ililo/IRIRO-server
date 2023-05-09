package team6.car.vehicle.response;

import team6.car.member.response.StatusEnum;

public class Message {

    private team6.car.member.response.StatusEnum status;
    private String message;
    private Object data;

    public Message() {
        this.status = StatusEnum.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }

}
