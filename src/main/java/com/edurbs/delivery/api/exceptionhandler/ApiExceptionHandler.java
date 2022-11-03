package com.edurbs.delivery.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Field> fields = new ArrayList<>();

        ex.getBindingResult().getAllErrors().stream().forEach( error ->           
            fields.add(
                new Field(
                    ((FieldError) error).getField(),
                    messageSource.getMessage(error, LocaleContextHolder.getLocale())
                )
            )
        );

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDateTime(LocalDateTime.now());
        problem.setTitle("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
        problem.setFields(fields);
        return handleExceptionInternal(ex, problem, headers, status, request);
    }
}
