package com.santandar.market.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DynamoDBConfigTest {

    @InjectMocks
    DynamoDBConfig dynamoDBConfig;

    @BeforeEach
    void setup(){
        ReflectionTestUtils.setField(dynamoDBConfig, "amazonAWSAccessKey", "test1");
        ReflectionTestUtils.setField(dynamoDBConfig, "amazonAWSSecretKey", "test231");
    }

    @Test
    public void testConfigurations() {
        AWSCredentials awsCredentials= dynamoDBConfig.amazonAWSCredentials();
        AmazonDynamoDB amazonDynamoDB=dynamoDBConfig.amazonDynamoDB();
        assertTrue(awsCredentials instanceof  AWSCredentials);
        assertTrue(amazonDynamoDB instanceof AmazonDynamoDB);
    }


}