package com.educacion.cursos.dto;

public class MensajeDTO {
    private String message;

    // Constructor vacío (Spring lo necesita)
    public MensajeDTO() {}

    // Getter y Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
