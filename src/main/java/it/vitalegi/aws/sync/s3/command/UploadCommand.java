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
        var targetKey = targetKey();
        log.info("Upload {} to {}", path, targetKey);
        s3.uploadObject(bucket, targetKey, path);
    }

    String targetKey() {
        if (!target.endsWith("/")) {
            throw new IllegalArgumentException("Invalid target path, must end with \"/\", actual: \"" + target + "\"");
        }
        return target + getFilename(path);
    }

    String getFilename(Path path) {
        return path.getFileName().toString();
    }
}
