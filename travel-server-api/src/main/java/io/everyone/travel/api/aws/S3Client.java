package io.everyone.travel.api.aws;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

public record S3Client(AmazonS3 s3, String url, String bucketName) {

    public String upload(InputStream input, String basePath, String contentType, int contentLength, Map<String, String> metadata) {

        String key = StringUtils.isEmpty(basePath)
            ? UUID.randomUUID().toString() : basePath + "/" + UUID.randomUUID().toString();

        var objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        objectMetadata.setContentLength(contentLength);
        objectMetadata.setUserMetadata(metadata);

        var objectRequest = new PutObjectRequest(this.bucketName, key, input, objectMetadata);

        return putObject(objectRequest);
    }

    private String putObject(PutObjectRequest objectRequest) {
        s3.putObject(objectRequest);
        return String.format("%s/%s/%s",        // `url/bucketName/key`
            this.url,
            this.bucketName,
            objectRequest.getKey()
        );
    }
}
