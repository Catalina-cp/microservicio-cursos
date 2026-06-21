package com.educacion.cursos.service;

import com.educacion.cursos.dto.S3ObjectDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AwsServiceImpl implements AwsService {

    private final S3Client s3Client;

    public AwsServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public List<S3ObjectDto> listarObjetos(String bucket) {

        ListObjectsV2Request request =
                ListObjectsV2Request.builder()
                        .bucket(bucket)
                        .build();

        ListObjectsV2Response response =
                s3Client.listObjectsV2(request);

        return response.contents()
                .stream()
                .map(obj ->
                        new S3ObjectDto(
                                obj.key(),
                                obj.size(),
                                obj.lastModified().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public byte[] descargarArchivo(String bucket, String key) {

        GetObjectRequest request =
                GetObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .build();

        ResponseBytes<GetObjectResponse> response =
                s3Client.getObjectAsBytes(request);

        return response.asByteArray();
    }

    @Override
    public void subirArchivo(
            String bucket,
            String key,
            MultipartFile file) {

        try {

            PutObjectRequest request =
                    PutObjectRequest.builder()
                            .bucket(bucket)
                            .key(key)
                            .contentType(file.getContentType())
                            .build();

            s3Client.putObject(
                    request,
                    RequestBody.fromInputStream(
                            file.getInputStream(),
                            file.getSize()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void moverArchivo(
            String bucket,
            String origen,
            String destino) {

        CopyObjectRequest copyRequest =
                CopyObjectRequest.builder()
                        .sourceBucket(bucket)
                        .sourceKey(origen)
                        .destinationBucket(bucket)
                        .destinationKey(destino)
                        .build();

        s3Client.copyObject(copyRequest);

        eliminarArchivo(bucket, origen);
    }

    @Override
    public void eliminarArchivo(
            String bucket,
            String key) {

        DeleteObjectRequest request =
                DeleteObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .build();

        s3Client.deleteObject(request);
    }

    @Override
public void subirArchivoLocal(
        String bucket,
        String key,
        String rutaArchivo) {

    File archivo = new File(rutaArchivo);

    PutObjectRequest request =
            PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();

    s3Client.putObject(
            request,
            archivo.toPath());
}
}