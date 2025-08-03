package it.vitalegi.aws.sync.s3;

import it.vitalegi.aws.sync.s3.command.Cli;
import it.vitalegi.aws.sync.s3.command.UploadCommand;
import it.vitalegi.aws.sync.s3.command.UploadCommandFactory;
import it.vitalegi.aws.sync.s3.model.Auth;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

public class App {

    public static void main(String[] args) {
        var command = uploadCommand(args);
        command.execute();
    }

    public static UploadCommand uploadCommand(String[] args) {
        var cli = cli(args);
        var factory = new UploadCommandFactory();
        factory.setCli(cli);
        var cmd = factory.build();
        var client = s3Client(cmd.getRegion(), cli.auth());
        cmd.setS3(awsS3Facade(client));
        return cmd;
    }

    public static Cli cli(String[] args) {
        return new Cli(parse(args));
    }

    public static CommandLine parse(String[] args) {
        var options = Cli.buildOptions();
        var parser = new DefaultParser();
        try {
            return parser.parse(options, args);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    protected static S3Client s3Client(Region region, Auth auth) {
        return new AwsS3Provider().s3Client(region, auth);
    }

    protected static AwsS3Facade awsS3Facade(S3Client client) {
        return new AwsS3Facade(client);
    }
}
