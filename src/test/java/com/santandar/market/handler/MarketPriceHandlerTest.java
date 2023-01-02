package com.santandar.market.handler;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santandar.market.LocalDynamoDBCreationRule;
import com.santandar.market.configuration.DynamoDBConfig;
import com.santandar.market.configuration.ServiceConfiguration;
import com.santandar.market.dynamodb.entities.Instrument;
import com.santandar.market.dynamodb.repositories.InstrumentRepository;
import com.santandar.market.mapper.InstrumentMapperImpl;
import com.santandar.market.processor.BaseMessageProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith({SpringExtension.class, LocalDynamoDBCreationRule.class, MockitoExtension.class})
@SpringBootTest
@ActiveProfiles("local")
@TestPropertySource(properties = {
        "amazon.dynamodb.endpoint=http://localhost:8000/",
        "amazon.aws.accesskey=test1",
        "amazon.aws.secretkey=test231"})
@ContextConfiguration(classes = {ServiceConfiguration.class, DynamoDBConfig.class, InstrumentMapperImpl.class, ObjectMapper.class})
class MarketPriceHandlerTest {
    @Autowired
    BaseMessageProcessor messageProcessor;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    InstrumentRepository repository;

    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    RestTemplate restTemplate;

    private DynamoDBMapper dynamoDBMapper;


    @BeforeEach
    public void setup() {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(Instrument.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);


        //...

        dynamoDBMapper.batchDelete(
                (List<Instrument>) repository.findAll());
    }

    @Test
    void testMarketPriceHandler() {
        ResponseEntity responseEntity = new ResponseEntity("Accepted", HttpStatus.ACCEPTED);
        Mockito.when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(),
                (Class<Object>) any()
                )
        ).thenReturn(responseEntity);
        String data = "106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01.001";
        MarketPriceHandler handler = new MarketPriceHandler(messageProcessor);
        // When
        Object response = handler.handleRequest(data, null);

        // Then
        assertEquals(response, "Lambda Function is invoked...." + data);
    }
}