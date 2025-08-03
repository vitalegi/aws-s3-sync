package it.vitalegi.aws.sync.s3.command;

import it.vitalegi.aws.sync.s3.model.Auth;
import lombok.AllArgsConstructor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import software.amazon.awssdk.regions.Region;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Properties;

@AllArgsConstructor
public class Cli {
    CommandLine cli;

    public static Options buildOptions() {
        var options = new Options();
        options.addOption("region", "region", true, "Region to connect to");
        options.addOption("bucket", "bucket", true, "Name of the bucket");
        options.addOption("auth", "auth", true, "Path of the auth config file");
        options.addOption("path", "path", true, "Path of the file to upload");
        options.addOption("target", "target", true, "Path where to load the file");
        return options;
    }

    public Region region() {
        var region = getString("region");
        return Region.of(region);
    }

    public String bucket() {
        return getString("bucket");
    }

    public Auth auth() {
        var path = authPath();
        if (path == null) {
            throw new NullPointerException("Auth path is null");
        }
        try (var is = Files.newInputStream(path)) {
            var props = new Properties();
            props.load(is);
            var accessKey = props.getProperty("accessKey");
            var secretKey = props.getProperty("secretKey");
            return Auth.builder().accessKeyId(accessKey).secretKey(secretKey).build();
        } catch (IOException e) {
            throw new RuntimeException("Error reading ", e);
        }
    }

    public Path authPath() {
        var path = getString("auth");
        return Path.of(path);
    }

    public Path path() {
        var path = getString("path");
        return Path.of(path);
    }

    public String target() {
        return getString("target");
    }

    public String getString(String key) {
        var value = cli.getOptionValue(key);
        if (value == null) {
            throw new NoSuchElementException("Missing field: " + key);
        }
        return value;
    }
}
