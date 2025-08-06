package it.vitalegi.aws.sync.s3.command;

import it.vitalegi.aws.sync.s3.AwsS3Facade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.regions.Region;

import java.nio.file.Path;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UploadCommandTests {

    @Test
    void commandIsCreated() {
        var path = Path.of("./src/test/resources/sample.txt");
        var uploadCommand = UploadCommand.builder() //
                .target("test/123/sample.txt") //
                .region(Region.AP_EAST_1) //
                .bucket("bucket1") //
                .path(path) //
                .build();

        var s3 = mock(AwsS3Facade.class);
        uploadCommand.setS3(s3);

        uploadCommand.execute();

        verify(s3, times(1)).uploadObject("bucket1", "test/123/sample.txt", path);
    }
}
