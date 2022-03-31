package com.devsuperior.bds04.resources.exceptions;

public class FieldMessage {
    private String message;
    private String fieldName;

    public FieldMessage(String message, String fieldName){
        this.message = message;
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public String getFieldName() {
        return fieldName;
    }
}
