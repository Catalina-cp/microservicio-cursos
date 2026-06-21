package com.educacion.cursos.controller;

import com.educacion.cursos.dto.InscripcionDTO;
import com.educacion.cursos.entity.Inscripcion;
import com.educacion.cursos.repository.InscripcionRepository;
import com.educacion.cursos.service.AwsService;
import com.educacion.cursos.service.InscripcionService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    private final InscripcionService service;
    private final AwsService awsService;
    private final InscripcionRepository inscripcionRepository;

    public InscripcionController(
            InscripcionService service,
            AwsService awsService,
            InscripcionRepository inscripcionRepository) {

        this.service = service;
        this.awsService = awsService;
        this.inscripcionRepository = inscripcionRepository;
    }

    @PostMapping
    public Inscripcion inscribir(
            @RequestBody InscripcionDTO dto) {

        return service.inscribir(dto);
    }

    @PostMapping("/{id}/subir-resumen")
    public String subirResumen(
            @PathVariable Long id) {

        Inscripcion inscripcion =
                inscripcionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Inscripción no encontrada"));

        String rutaArchivo =
                "resumenes/resumen_" +
                inscripcion.getId() +
                ".txt";

        String key =
                inscripcion.getId() +
                "/resumen_" +
                inscripcion.getId() +
                ".txt";

        awsService.subirArchivoLocal(
                "mibucketduocs3ccp",
                key,
                rutaArchivo);

        return "Resumen subido correctamente al bucket";
    }
}