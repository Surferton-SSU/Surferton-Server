package com.surferton.server.service;

import com.surferton.server.config.AwsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class S3Service {

    private final String bucketName;
    private final AwsConfig awsConfig;

    // 이미지와 PDF 파일 확장자 목록
    private static final List<String> FILE_EXTENSIONS = Arrays.asList("image/jpeg", "image/png", "image/jpg", "image/webp", "application/pdf");

    public S3Service(@Value("${aws-property.s3-bucket-name}") final String bucketName, AwsConfig awsConfig) {
        this.bucketName = bucketName;
        this.awsConfig = awsConfig;
    }

    public String uploadFile(String directoryPath, MultipartFile file) throws IOException {
        String key = directoryPath + generateFileName(file);
        final S3Client s3Client = awsConfig.getS3Client();

        validateExtension(file);
        validateFileSize(file);

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .contentDisposition("inline")
                .build();

        RequestBody requestBody = RequestBody.fromBytes(file.getBytes());
        s3Client.putObject(request, requestBody);
        return key;
    }

    public void deleteFile(String key) throws IOException {
        final S3Client s3Client = awsConfig.getS3Client();

        s3Client.deleteObject((DeleteObjectRequest.Builder builder) ->
                builder.bucket(bucketName)
                        .key(key)
                        .build()
        );
    }

    // 파일명 생성 로직: 확장자에 맞게 파일 이름 생성
    private String generateFileName(MultipartFile file) {
        String extension = getFileExtension(file.getContentType());
        return UUID.randomUUID() + extension;
    }

    // 확장자 반환: Content-Type을 기반으로 확장자 추출
    private String getFileExtension(String contentType) {
        switch (contentType) {
            case "image/jpeg":
                return ".jpg";
            case "image/png":
                return ".png";
            case "image/webp":
                return ".webp";
            case "application/pdf":
                return ".pdf";
            default:
                throw new RuntimeException("지원되지 않는 파일 형식입니다.");
        }
    }

    // 확장자 확인
    private void validateExtension(MultipartFile file) {
        String contentType = file.getContentType();
        if (!FILE_EXTENSIONS.contains(contentType)) {
            throw new RuntimeException("허용된 확장자는 jpg, png, webp, pdf만 가능합니다.");
        }
    }

    private static final Long MAX_FILE_SIZE = 5 * 1024 * 1024L;

    // 파일 크기 검증
    private void validateFileSize(MultipartFile file) {
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new RuntimeException("파일 크기는 5MB를 넘을 수 없습니다.");
        }
    }
}
