package com.usermind.usermindsdk.baselib.writers.s3;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.retry.RetryPolicy;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;

public class S3ClientCreator {

    //TODO - create a single connection instead of repeatedly creating new ones
    private S3ClientCreator(){};

    private static ClientConfiguration getClientConfiguration(Integer socketTimeout, RetryPolicy retryPolicy) {
        ClientConfiguration clientConfiguration = new ClientConfiguration();

        clientConfiguration.setSocketTimeout(socketTimeout);

        clientConfiguration.setRetryPolicy(retryPolicy);
        clientConfiguration.setMaxErrorRetry(retryPolicy.getMaxErrorRetry());

        return clientConfiguration;
    }

    public static AmazonS3Client getS3Client(S3Config s3Config, RetryPolicy retryPolicy) {
        AmazonS3Client amazonS3Client =
                new AmazonS3Client(new DefaultAWSCredentialsProviderChain(),
                        getClientConfiguration(s3Config.getSocketTimeout(), retryPolicy));
        amazonS3Client.setEndpoint(s3Config.getEndpoint());

        if (s3Config.getPathStyle()) {
            S3ClientOptions options = S3ClientOptions.builder()
                    .setPathStyleAccess(true)
                    // Primary use case is for using fake-s3, which currently fails when chunked encoding
                    // is enabled: https://github.com/jubos/fake-s3/issues/164
                    .disableChunkedEncoding()
                    .build();
            amazonS3Client.setS3ClientOptions(options);
        }

        // In the dev environment we want to automatically create s3 buckets when the docker stack
        // comes up.  The config-docker.json should have "createBucket" as true for s3 and s3writer
        if (s3Config.getCreateBucket()) {
            String bucketName = s3Config.getBucket();
            if (!amazonS3Client.doesBucketExist(bucketName)) {
                amazonS3Client.createBucket(bucketName);
            }
        }

        return amazonS3Client;
    }

}
