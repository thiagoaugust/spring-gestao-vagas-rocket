package br.com.thiago.gestaovagas.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDto>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ErrorMessageDto> dto = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
           String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            ErrorMessageDto errorMessageDto = new ErrorMessageDto(message, fieldError.getField());
            dto.add(errorMessageDto);
        });
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
