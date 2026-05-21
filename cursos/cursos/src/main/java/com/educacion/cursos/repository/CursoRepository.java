package com.educacion.cursos.repository;

import com.educacion.cursos.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository
        extends JpaRepository<Curso, Long> {
}