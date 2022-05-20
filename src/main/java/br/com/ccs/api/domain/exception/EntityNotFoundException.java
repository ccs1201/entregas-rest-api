package br.com.ccs.api.domain.exception;

import lombok.Setter;

@Setter
public class EntityNotFoundException extends CrudException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
