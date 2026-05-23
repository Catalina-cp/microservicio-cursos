package com.educacion.cursos.controller;

import com.educacion.cursos.entity.Curso;
import com.educacion.cursos.service.CursoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

@RequestMapping("/cursos")

public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping

    public List<Curso> listarCursos() {
        return service.listarCursos();
    }

    @PostMapping

    public Curso guardarCurso(
            @RequestBody Curso curso) {

        return service.guardarCurso(curso);
    }
}