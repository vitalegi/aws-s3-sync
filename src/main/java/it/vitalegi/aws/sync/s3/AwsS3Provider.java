package it.vitalegi.aws.sync.s3;

import it.vitalegi.aws.sync.s3.model.Auth;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Slf4j
public class AwsS3Provider {
    public S3Client s3Client(Region region, Auth auth) {
        log.info("Build S3 client with region={}", region);
        var s3 = S3Client.builder() //
                .region(region) //
                .credentialsProvider(StaticCredentialsProvider.create(credentials(auth))) //
                .build();
        log.info("AWS S3 done");
        return s3;
    }

    protected AwsCredentials credentials(Auth auth) {
        return AwsBasicCredentials.create(auth.getAccessKeyId(), auth.getSecretKey());
    }
}
