package com.educacion.cursos.service;

import com.educacion.cursos.dto.S3ObjectDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AwsService {

    List<S3ObjectDto> listarObjetos(String bucket);

    byte[] descargarArchivo(String bucket, String key);

    void subirArchivo(String bucket, String key, MultipartFile file);

    void moverArchivo(String bucket, String origen, String destino);

    void eliminarArchivo(String bucket, String key);

    void subirArchivoLocal(
        String bucket,
        String key,
        String rutaArchivo);
}