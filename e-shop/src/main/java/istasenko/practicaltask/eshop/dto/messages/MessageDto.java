package istasenko.practicaltask.eshop.dto.messages;


import istasenko.practicaltask.eshop.util.MessageType;

import java.util.Objects;

public class MessageDto {
    private String message;
    private MessageType type;

    public MessageDto() {
    }

    public MessageDto(String message, MessageType type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageDto that = (MessageDto) o;
        return Objects.equals(message, that.message) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, type);
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "message: " + message +
                ", type:" + type +
                '}';
    }
}
