package com.educacion.cursos.service;

import com.educacion.cursos.dto.ResumenInscripcionMensaje;
import com.educacion.cursos.entity.Inscripcion;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductorService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Método original, para el endpoint de prueba /producer/send
    public void enviarMensaje(String message) {
        rabbitTemplate.convertAndSend("myQueue", message);
    }

    // Método nuevo: manda el resumen real de una inscripción
    public void enviarResumenInscripcion(Inscripcion inscripcion) {

        ResumenInscripcionMensaje mensaje = new ResumenInscripcionMensaje(
                inscripcion.getId(),
                inscripcion.getEstudiante(),
                inscripcion.getCursos(),
                inscripcion.getTotal()
        );

        rabbitTemplate.convertAndSend("myQueue", mensaje);
    }
}