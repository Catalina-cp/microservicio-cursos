package com.educacion.cursos.dto;

import java.util.List;

public class InscripcionDTO {

    private String estudiante;

    private List<Long> cursosIds;

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public List<Long> getCursosIds() {
        return cursosIds;
    }

    public void setCursosIds(List<Long> cursosIds) {
        this.cursosIds = cursosIds;
    }
}