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
import java.time.OffsetDateTime;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDto dto = new ErrorDto();

        dto.setDataHora(OffsetDateTime.now());
        dto.setStatus(status.value());
        dto.setMensagem("Erro em um ou mais campos, verifique...");

        ex.getFieldErrors().forEach(error -> dto.getCampos().add(
                dto.new Campo(error.getField(), messageSource.getMessage(error, LocaleContextHolder.getLocale()))));

        return handleExceptionInternal(ex, dto, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> entityNotFoundExceptionHandler(CrudException ex, WebRequest request) {

        ErrorDto dto = new ErrorDto();

        dto.setDataHora(OffsetDateTime.now());
        dto.setStatus(HttpStatus.NOT_FOUND.value());
        dto.setMensagem(ex.getLocalizedMessage());

        return handleExceptionInternal(ex, dto, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


    @ExceptionHandler(CrudException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> cruExceptionHandler(CrudException ex, WebRequest request) {

        ErrorDto dto = new ErrorDto();

        dto.setDataHora(OffsetDateTime.now());
        dto.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        dto.setMensagem(ex.getLocalizedMessage());

        return handleExceptionInternal(ex, dto, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex, WebRequest request){
        ErrorDto dto = new ErrorDto();

        dto.setDataHora(OffsetDateTime.now());
        dto.setStatus(HttpStatus.BAD_REQUEST.value());
        dto.setMensagem(ex.getMessage());

        return handleExceptionInternal(ex, dto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> propertyReferenceExceptionHandler(PropertyReferenceException ex, WebRequest request){
        ErrorDto dto = new ErrorDto();

        dto.setDataHora(OffsetDateTime.now());
        dto.setStatus(HttpStatus.BAD_REQUEST.value());
        dto.setMensagem(ex.getMessage());

        return handleExceptionInternal(ex, dto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> constraintViolationExceptionHandler(ConstraintViolationException ex, WebRequest request){
        ErrorDto dto = new ErrorDto();

        dto.setDataHora(OffsetDateTime.now());
        dto.setStatus(HttpStatus.BAD_REQUEST.value());
        dto.setMensagem(ex.getMessage());

        ex.getConstraintViolations().forEach(error -> dto.getCampos().add(
                dto.new Campo(error.getPropertyPath().toString(),error.getMessage())));

        return handleExceptionInternal(ex, dto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> invalidDataAccessApiUsageExceptionHandler(InvalidDataAccessApiUsageException ex, WebRequest request){
        ErrorDto dto = new ErrorDto();

        dto.setDataHora(OffsetDateTime.now());
        dto.setStatus(HttpStatus.BAD_REQUEST.value());
        dto.setMensagem(ex.getMessage());

        return handleExceptionInternal(ex, dto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }
}
