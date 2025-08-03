package it.vitalegi.aws.sync.s3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class S3Obj {
    private String key;
    private Instant lastModified;
    private String eTag;
    private List<String> checksumAlgorithm;
    private Long size;
    private String storageClass;
    private String ownerId;
}
