package io.everyone.travel.api.aws;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.s3")
public record AwsProperties(
    String url,
    String bucketName,
    String region,
    String accessKey,
    String secretKey) {
}
