package com.educacion.cursos.service;

import com.educacion.cursos.entity.Inscripcion;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class ArchivoService {

    public String generarResumen(Inscripcion inscripcion) {

        try {

            File carpeta = new File("resumenes");

            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            String nombreArchivo =
                    "resumenes/resumen_" +
                    inscripcion.getId() +
                    ".txt";

            FileWriter writer =
                    new FileWriter(nombreArchivo);

            writer.write(
                    "RESUMEN INSCRIPCION\n\n" +
                    "ID: " + inscripcion.getId() + "\n" +
                    "Estudiante: " + inscripcion.getEstudiante() + "\n" +
                    "Cursos: " + inscripcion.getCursos() + "\n" +
                    "Total: $" + inscripcion.getTotal()
            );

            writer.close();

            return nombreArchivo;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Error al generar archivo",
                    e
            );
        }
    }
}