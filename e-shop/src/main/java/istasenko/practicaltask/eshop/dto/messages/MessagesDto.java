package istasenko.practicaltask.eshop.dto.messages;

import java.util.List;
import java.util.Objects;

public class MessagesDto {
    private List<MessageDto> messages;

    public MessagesDto() {
    }

    public MessagesDto(List<MessageDto> messages) {
        this.messages = messages;
    }

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "MessagesDto{" +
                "message=" + messages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessagesDto that = (MessagesDto) o;
        return Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages);
    }
}
