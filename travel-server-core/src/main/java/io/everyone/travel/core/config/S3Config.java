package io.everyone.travel.core.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import io.everyone.travel.core.aws.AwsS3Properties;
import io.everyone.travel.core.aws.S3Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Bean
    AmazonS3 amazonS3(AwsS3Properties awsProperties) {
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
                new AwsClientBuilder.EndpointConfiguration(
                    awsProperties.url(),
                    awsProperties.region()
                )
            )
            .enablePathStyleAccess()
            .build();
    }

    @Bean
    S3Client s3Client(AmazonS3 s3, AwsS3Properties awsProperties) {
        return new S3Client(s3, awsProperties.url(), awsProperties.bucketName());
    }

}
