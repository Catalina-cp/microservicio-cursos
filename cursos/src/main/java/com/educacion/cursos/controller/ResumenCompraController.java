package com.educacion.cursos.controller;

import com.educacion.cursos.h2.entity.ResumenCompra;
import com.educacion.cursos.h2.repository.ResumenCompraRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resumenes-compra")
public class ResumenCompraController {

    private final ResumenCompraRepository resumenCompraRepository;

    public ResumenCompraController(ResumenCompraRepository resumenCompraRepository) {
        this.resumenCompraRepository = resumenCompraRepository;
    }

    @GetMapping
    public List<ResumenCompra> listar() {
        return resumenCompraRepository.findAll();
    }
}