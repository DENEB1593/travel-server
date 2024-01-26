package io.everyone.travel.api.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Bean
    AmazonS3 amazonS3(AwsProperties awsProperties) {
        return AmazonS3ClientBuilder.standard()
            .withCredentials(
                new AWSStaticCredentialsProvider(
                    new BasicAWSCredentials(
                        awsProperties.accessKey(),
                        awsProperties.secretKey()
                    )
                )
            )
            .withEndpointConfiguration(
                new EndpointConfiguration(
                    awsProperties.url(),
                    awsProperties.region()
                )
            )
            .enablePathStyleAccess()
            .build();
    }

    @Bean
    S3Client s3Client(AmazonS3 s3, AwsProperties awsProperties) {
        return new S3Client(s3, awsProperties.url(), awsProperties.bucketName());
    }

}
