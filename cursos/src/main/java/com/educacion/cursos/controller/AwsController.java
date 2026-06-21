package com.educacion.cursos.controller;

import com.educacion.cursos.dto.S3ObjectDto;
import com.educacion.cursos.service.AwsService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/s3")
public class AwsController {

    private final AwsService awsService;

    public AwsController(AwsService awsService) {
        this.awsService = awsService;
    }

    @GetMapping("/{bucket}/objects")
    public List<S3ObjectDto> listarObjetos(
            @PathVariable String bucket) {

        return awsService.listarObjetos(bucket);
    }

    @GetMapping("/{bucket}/object")
    public ResponseEntity<byte[]> descargarArchivo(
            @PathVariable String bucket,
            @RequestParam String key) {

        byte[] archivo =
                awsService.descargarArchivo(bucket, key);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + key)
                .contentType(
                        MediaType.APPLICATION_OCTET_STREAM)
                .body(archivo);
    }

    @PostMapping("/{bucket}/object")
    public ResponseEntity<String> subirArchivo(
            @PathVariable String bucket,
            @RequestParam String key,
            @RequestParam MultipartFile file) {

        awsService.subirArchivo(bucket, key, file);

        return ResponseEntity.ok(
                "Archivo subido correctamente");
    }

    @PostMapping("/{bucket}/move")
    public ResponseEntity<String> moverArchivo(
            @PathVariable String bucket,
            @RequestParam String sourceKey,
            @RequestParam String destKey) {

        awsService.moverArchivo(
                bucket,
                sourceKey,
                destKey);

        return ResponseEntity.ok(
                "Archivo movido correctamente");
    }

    @DeleteMapping("/{bucket}/object")
    public ResponseEntity<String> eliminarArchivo(
            @PathVariable String bucket,
            @RequestParam String key) {

        awsService.eliminarArchivo(
                bucket,
                key);

        return ResponseEntity.ok(
                "Archivo eliminado");
    }
}