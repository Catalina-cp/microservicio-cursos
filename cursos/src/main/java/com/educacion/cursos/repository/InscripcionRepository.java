package com.educacion.cursos.repository;

import com.educacion.cursos.entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository
        extends JpaRepository<Inscripcion, Long> {
}