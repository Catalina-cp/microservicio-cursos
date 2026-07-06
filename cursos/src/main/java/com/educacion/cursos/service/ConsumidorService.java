package com.educacion.cursos.service;

import com.educacion.cursos.dto.ResumenInscripcionMensaje;
import com.educacion.cursos.h2.entity.ResumenCompra;
import com.educacion.cursos.h2.repository.ResumenCompraRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "myQueue")
public class ConsumidorService {

    private final ResumenCompraRepository resumenCompraRepository;

    public ConsumidorService(ResumenCompraRepository resumenCompraRepository) {
        this.resumenCompraRepository = resumenCompraRepository;
    }

    @RabbitHandler
    public void recibirTexto(String mensaje) {
        System.out.println("Mensaje de texto recibido: " + mensaje);
    }

    @RabbitHandler
    public void recibirResumen(ResumenInscripcionMensaje mensaje) {

        System.out.println("Mensaje recibido: " + mensaje);

        ResumenCompra resumenCompra = new ResumenCompra();
        resumenCompra.setInscripcionId(mensaje.getInscripcionId());
        resumenCompra.setEstudiante(mensaje.getEstudiante());
        resumenCompra.setCursos(mensaje.getCursos());
        resumenCompra.setTotal(mensaje.getTotal());

        resumenCompraRepository.save(resumenCompra);

        System.out.println("Resumen de compra guardado en H2 con id: " + resumenCompra.getId());
    }
}