package com.devsuperior.bds04.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

    private List<FieldMessage> errors = new ArrayList<>();

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addMessage(String fieldName, String message) {
        errors.add(new FieldMessage(message,fieldName));
    }
}
