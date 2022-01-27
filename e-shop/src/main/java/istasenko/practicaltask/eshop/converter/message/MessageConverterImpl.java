package istasenko.practicaltask.eshop.converter.message;


import istasenko.practicaltask.eshop.dto.messages.MessageDto;
import istasenko.practicaltask.eshop.dto.messages.MessagesDto;
import istasenko.practicaltask.eshop.util.MessageType;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageConverterImpl implements MessageConverter {

    @Override
    public MessagesDto fromErrorList(List<FieldError> errors) {
        List<MessageDto> errorsStrings= new ArrayList<>();
        for (FieldError error: errors) {
            MessageDto messagesDto = new MessageDto(error.getDefaultMessage(), MessageType.ERROR);
            errorsStrings.add(messagesDto);
        }
        return new MessagesDto(errorsStrings);
    }

    @Override
    public MessagesDto fromMessage(String errorMessage, MessageType type) {
        List<MessageDto> errorsStrings= new ArrayList<>();
        errorsStrings.add(new MessageDto(errorMessage, type));
        return new MessagesDto(errorsStrings);
    }
}
