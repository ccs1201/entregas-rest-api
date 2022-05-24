package br.com.ccs.api.exception;

import br.com.ccs.api.domain.exception.CrudException;
import br.com.ccs.api.domain.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.OffsetDateTime;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private MessageSource messageSource;

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDto dto = new ErrorDto();

        dto.setDataHora(OffsetDateTime.now());
        dto.setStatus(status.value());
        dto.setMensagem("Erro em um ou mais campos, verifique...");

        ex.getFieldErrors().forEach(error -> dto.getCampos().add(
                dto.new Campo(error.getField(), messageSource.getMessage(error, LocaleContextHolder.getLocale()))));

        return createHandlerException(ex, HttpStatus.BAD_REQUEST, request, dto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> entityNotFoundExceptionHandler(CrudException ex, WebRequest request) {

        return createHandlerException(ex, HttpStatus.NOT_FOUND, request);
    }


    @ExceptionHandler(CrudException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> cruExceptionHandler(CrudException ex, WebRequest request) {
        return createHandlerException(ex, HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex, WebRequest request) {
        return createHandlerException(ex, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> propertyReferenceExceptionHandler(PropertyReferenceException ex, WebRequest request) {
        return createHandlerException(ex, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> constraintViolationExceptionHandler(ConstraintViolationException ex, WebRequest request) {
        ErrorDto dto = new ErrorDto();

        dto.setDataHora(OffsetDateTime.now());
        dto.setStatus(HttpStatus.BAD_REQUEST.value());
        dto.setMensagem(ex.getMessage());

        ex.getConstraintViolations().forEach(error -> dto.getCampos().add(
                dto.new Campo(error.getPropertyPath().toString(), error.getMessage())));

        return createHandlerException(ex, HttpStatus.BAD_REQUEST, request, dto);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> invalidDataAccessApiUsageExceptionHandler(InvalidDataAccessApiUsageException ex, WebRequest request) {

        return createHandlerException(ex, HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> sqlLIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException ex, WebRequest request) {
        return createHandlerException(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(EntregaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> entregaExceptionHandler(EntregaException ex, WebRequest request) {
        return createHandlerException(ex, HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<Object> createHandlerException(Exception exception, HttpStatus httpStatus, WebRequest request) {
        ErrorDto dto = new ErrorDto();

        dto.setDataHora(OffsetDateTime.now());
        dto.setStatus(httpStatus.value());
        dto.setMensagem(exception.getMessage());

        return handleExceptionInternal(exception, dto, new HttpHeaders(), httpStatus, request);
    }

    private ResponseEntity<Object> createHandlerException(Exception exception, HttpStatus httpStatus, WebRequest request, ErrorDto errorDto) {

        return handleExceptionInternal(exception, errorDto, new HttpHeaders(), httpStatus, request);
    }
}
