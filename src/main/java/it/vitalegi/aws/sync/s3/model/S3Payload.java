package it.vitalegi.aws.sync.s3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class S3Payload {
    byte[] content;
    String key;
    String name;
    String etag;
    String contentType;
}
