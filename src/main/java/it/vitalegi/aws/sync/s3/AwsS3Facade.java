package it.vitalegi.aws.sync.s3;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Path;

@Slf4j
@AllArgsConstructor
public class AwsS3Facade {
    protected S3Client s3;

    public void uploadObject(String bucket, String key, Path path) {
        log.info("Upload object key={}, path={}", key, path);
        var request = PutObjectRequest.builder().key(key).bucket(bucket).build();
        s3.putObject(request, path);
        log.info("Upload done {}", key);
    }
}
