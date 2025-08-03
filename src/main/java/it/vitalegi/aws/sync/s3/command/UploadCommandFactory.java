package it.vitalegi.aws.sync.s3.command;

import lombok.Setter;

@Setter
public class UploadCommandFactory {
    Cli cli;

    public UploadCommand build() {
        return UploadCommand.builder() //
                .auth(cli.auth()) //
                .bucket(cli.bucket()) //
                .region(cli.region()) //
                .path(cli.path()) //
                .target(cli.target()) //
                .build();
    }

}
