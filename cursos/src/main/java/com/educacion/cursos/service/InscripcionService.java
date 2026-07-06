package com.educacion.cursos.service;

import com.educacion.cursos.dto.InscripcionDTO;
import com.educacion.cursos.entity.Curso;
import com.educacion.cursos.entity.Inscripcion;
import com.educacion.cursos.repository.CursoRepository;
import com.educacion.cursos.repository.InscripcionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionService {

    private final CursoRepository cursoRepository;
    private final InscripcionRepository inscripcionRepository;
    private final ArchivoService archivoService;
    private final ProductorService productorService;

    public InscripcionService(
            CursoRepository cursoRepository,
            InscripcionRepository inscripcionRepository,
            ArchivoService archivoService,
            ProductorService productorService) {

        this.cursoRepository = cursoRepository;
        this.inscripcionRepository = inscripcionRepository;
        this.archivoService = archivoService;
        this.productorService = productorService;
    }

    public Inscripcion inscribir(InscripcionDTO dto) {

        List<Curso> cursos =
                cursoRepository.findAllById(dto.getCursosIds());

        double total = cursos.stream()
                .mapToDouble(Curso::getCosto)
                .sum();

        String nombresCursos = cursos.stream()
                .map(Curso::getNombre)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudiante(dto.getEstudiante());
        inscripcion.setCursos(nombresCursos);
        inscripcion.setTotal(total);

        Inscripcion guardada =
                inscripcionRepository.save(inscripcion);

        archivoService.generarResumen(guardada);

        // Semana 7: enviamos el resumen de la inscripcion a la cola MQ
        productorService.enviarResumenInscripcion(guardada);

        return guardada;
    }
}