package com.educacion.cursos.controller;

import com.educacion.cursos.dto.MensajeDTO;
import com.educacion.cursos.service.ProductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class ProductorController {

    @Autowired
    private ProductorService productorService;

    @PostMapping("/send")
    public String sendMessage(@RequestBody MensajeDTO dto) {
        productorService.enviarMensaje(dto.getMessage());
        return "Mensaje enviado a RabbitMQ: " + dto.getMessage();
    }
}
