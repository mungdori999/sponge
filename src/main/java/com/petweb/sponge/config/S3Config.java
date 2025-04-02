package com.petweb.sponge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class S3Config {

	private final String accessKey;
	private final String secretKey;
	private final String region;

	public S3Config(@Value("${cloud.aws.credentials.access-key}")String accessKey,
					@Value("${cloud.aws.credentials.secret-key}")String secretKey,
					@Value("${cloud.aws.region.static}")String region) {
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.region = region;
	}

	@Bean
	public S3Client s3Client() {
		return S3Client.builder()
				.region(Region.of(region))
				.credentialsProvider(StaticCredentialsProvider.create(
						AwsBasicCredentials.create(accessKey, secretKey)
				))
				.build();
	}
	@Bean
	public S3Presigner s3Presigner() {
		return S3Presigner.builder()
				.region(Region.of(region))  // 동일한 region 사용
				.credentialsProvider(StaticCredentialsProvider.create(
						AwsBasicCredentials.create(accessKey, secretKey) // 같은 크레덴셜 사용
				))
				.build();
	}

}

