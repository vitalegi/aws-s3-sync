package it.vitalegi.aws.sync.s3.command;

import it.vitalegi.aws.sync.s3.AwsS3Facade;
import it.vitalegi.aws.sync.s3.model.Auth;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.regions.Region;

import java.nio.file.Path;

@Slf4j
@Builder
@Getter
@Setter
public class UploadCommand implements Command {
    AwsS3Facade s3;
    Auth auth;
    Region region;
    String bucket;
    Path path;
    String target;

    @Override
    public void execute() {
        log.info("Region {}, bucket {}", region, bucket);
        log.info("Upload {} to {}", path, target);
        s3.uploadObject(bucket, target, path);
    }
}
