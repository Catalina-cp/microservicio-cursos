package com.educacion.cursos.service;

import com.educacion.cursos.entity.Curso;
import com.educacion.cursos.repository.CursoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public List<Curso> listarCursos() {
        return repository.findAll();
    }

    public Curso guardarCurso(Curso curso) {
        return repository.save(curso);
    }
}