package istasenko.practicaltask.eshop.controller.exception;

import istasenko.practicaltask.eshop.converter.message.MessageConverter;
import istasenko.practicaltask.eshop.dto.messages.MessagesDto;
import istasenko.practicaltask.eshop.exception.EmptyBasketException;
import istasenko.practicaltask.eshop.exception.ProductNotFoundException;
import istasenko.practicaltask.eshop.util.MessageType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class EShopExceptionHandler {

    private static final Logger logger = LogManager.getLogger("DebugLevelLog");

    @Autowired
    MessageConverter messageConverter;

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<MessagesDto> getAuthorizationError(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> errorMessages = result.getFieldErrors();
        MessagesDto errorMessageDto = messageConverter.fromErrorList(errorMessages);
        logger.info(String.format("in the isAuthorizationError with exception: %s", errorMessageDto));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorMessageDto);
    }

    @ExceptionHandler(value = EmptyBasketException.class)
    public ResponseEntity<MessagesDto> getEmptyBasket(EmptyBasketException exception) {
        String errorMessage = exception.getMessage();
        MessagesDto errorMessageDto = messageConverter.fromMessage(errorMessage, MessageType.ERROR);
        logger.debug(String.format("in the bed credential with exception: %s", errorMessage ));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorMessageDto);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<MessagesDto> getNotFound(Exception exception) {
        String errorMessage = exception.getMessage();
        MessagesDto errorMessageDto = messageConverter.fromMessage(errorMessage, MessageType.ERROR);
        logger.debug(String.format("in the bed credential with exception: %s", errorMessage ));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).body(errorMessageDto);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<MessagesDto> getAccessDenied(Exception exception) {
        String errorMessage = exception.getMessage();
        MessagesDto errorMessageDto = messageConverter.fromMessage(errorMessage, MessageType.ERROR);
        logger.debug(String.format("in the  getAccessDenied with exception: %s", errorMessageDto));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).contentType(MediaType.APPLICATION_JSON).body(errorMessageDto);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<MessagesDto> getCrackedUrl(RuntimeException exception) {
        String errorMessage = exception.getMessage();
        MessagesDto errorMessageDto = messageConverter.fromMessage(errorMessage, MessageType.ERROR);
        logger.debug(String.format("In the product not found exception: %s", errorMessageDto));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorMessageDto);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<MessagesDto> isException(Exception exception) {
        String errorMessage = exception.getMessage();
        exception.printStackTrace();
        MessagesDto errorMessageDto = messageConverter.fromMessage(errorMessage, MessageType.ERROR);
        logger.debug(String.format("in the isException with exception: %s", errorMessageDto));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(errorMessageDto);
    }

}

