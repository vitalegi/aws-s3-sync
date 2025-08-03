package it.vitalegi.aws.sync.s3.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Auth {
    String accessKeyId;
    String secretKey;
}
