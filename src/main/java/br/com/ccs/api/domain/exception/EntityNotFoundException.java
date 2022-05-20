package br.com.ccs.api.domain.exception;

public class EntityNotFoundException extends CrudException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
