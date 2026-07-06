package com.educacion.cursos.dto;

import java.io.Serializable;

public class ResumenInscripcionMensaje implements Serializable {

    private Long inscripcionId;
    private String estudiante;
    private String cursos;
    private Double total;

    public ResumenInscripcionMensaje() {
    }

    public ResumenInscripcionMensaje(Long inscripcionId, String estudiante, String cursos, Double total) {
        this.inscripcionId = inscripcionId;
        this.estudiante = estudiante;
        this.cursos = cursos;
        this.total = total;
    }

    public Long getInscripcionId() {
        return inscripcionId;
    }

    public void setInscripcionId(Long inscripcionId) {
        this.inscripcionId = inscripcionId;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getCursos() {
        return cursos;
    }

    public void setCursos(String cursos) {
        this.cursos = cursos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}