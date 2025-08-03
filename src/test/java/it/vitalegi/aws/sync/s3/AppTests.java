package it.vitalegi.aws.sync.s3;

import it.vitalegi.aws.sync.s3.model.Auth;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.regions.Region;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AppTests {

    @Test
    void given_uploadCmd_then_cmdIsInitialized() {
        var actual = App.uploadCommand(new String[]{"--auth", "./src/test/resources/auth1.properties", "--region", Region.AP_EAST_1.id(), "--bucket", "b", "--path", "./src/test/resources/sample.txt", "--target", "/test/123/"});
        assertEquals(Auth.builder().accessKeyId("ak").secretKey("sk").build(), actual.getAuth());
        assertEquals(Region.AP_EAST_1, actual.getRegion());
        assertEquals("b", actual.getBucket());
        assertEquals(Path.of("./src/test/resources/sample.txt"), actual.getPath());
        assertEquals("/test/123/", actual.getTarget());
    }
}
