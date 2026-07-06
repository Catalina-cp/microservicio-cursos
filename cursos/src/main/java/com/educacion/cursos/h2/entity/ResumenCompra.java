package com.educacion.cursos.h2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "resumen_compra")
public class ResumenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "inscripcion_id")
    private Long inscripcionId;

    @Column(name = "estudiante")
    private String estudiante;

    @Column(name = "cursos", length = 1000)
    private String cursos;

    @Column(name = "total")
    private Double total;

    public ResumenCompra() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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