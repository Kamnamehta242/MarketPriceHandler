/*
package com.santandar.market;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SocketUtils;
@Configuration
public class DynamoDbTestConfig {
    @Bean

    public AmazonDynamoDB amazonDynamoDB() {

        int port = SocketUtils.findAvailableTcpPort();

        DynamoDBProxyServer server;       //create a server locally.

        try {

            server = ServerRunner.createServerFromCommandLineArgs(new String[]{"-inMemory", "-port", String.valueOf(port)});

            server.start();     //start the server on available port.

            AmazonDynamoDB db = AmazonDynamoDBClientBuilder.standard()

                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:" + port, "us-west-2"))

                    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("access", "secret")))

                    .build();

            return db;

        } catch (Exception e) {

            throw new RuntimeException(e);

        }



    }

    // Create a DynamoDB Mapper bean to be used in your application.

    @Bean

    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB db) {

        DynamoDBMapperConfig builder = new DynamoDBMapperConfig.Builder()

                .build();

        return new DynamoDBMapper(db, builder);

    }
}
*/
