package istasenko.practicaltask.eshop.converter.message;


import istasenko.practicaltask.eshop.dto.messages.MessagesDto;
import istasenko.practicaltask.eshop.util.MessageType;
import org.springframework.validation.FieldError;

import java.util.List;

public interface MessageConverter {
    MessagesDto fromErrorList(List<FieldError> errors);
    MessagesDto fromMessage(String errorMessage, MessageType type);

}
