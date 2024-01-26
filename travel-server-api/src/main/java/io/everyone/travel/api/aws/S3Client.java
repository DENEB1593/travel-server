package io.everyone.travel.api.aws;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

public record S3Client(
    AmazonS3 s3, String url, String bucketName) {

    public String upload(File file) {
        PutObjectRequest objectRequest = new PutObjectRequest(bucketName, file.getName(), file);
        return putObject(objectRequest);
    }

    public String upload(String bucketName, String key, InputStream input, Map<String, String> metadata) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setUserMetadata(metadata);
        PutObjectRequest objectRequest = new PutObjectRequest(bucketName, key, input, objectMetadata);
        return null;
    }

    private String putObject(PutObjectRequest objectRequest) {
        s3.putObject(objectRequest);
        return String.format("%s/%s/%s",        // `url/bucketName/key`
            url,
            bucketName,
            objectRequest.getKey()
        );
    }
}
