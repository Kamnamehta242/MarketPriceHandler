package com.santandar.market.dynamodb.repositories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;


import com.santandar.market.LocalDynamoDBCreationRule;
import com.santandar.market.dynamodb.entities.Instrument;
import com.santandar.market.utilities.FXDateTime;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class, LocalDynamoDBCreationRule.class})
@SpringBootTest
@ActiveProfiles("local")
@TestPropertySource(properties = {
        "amazon.dynamodb.endpoint=http://localhost:8000/",
        "amazon.aws.accesskey=test1",
        "amazon.aws.secretkey=test231"})
class InstrumentRepositoryTest {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;


    @Autowired
    InstrumentRepository repository;

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
    public void testInstrument() throws ParseException {
        Instrument productInfo = new Instrument("1", "EUR/USD", 1.1000, 1.2000, FXDateTime.convertToDate("01-06-2020 12:01:01.001"), 1.0000, 1.3000, FXDateTime.convertToDate("01-06-2020 12:01:01.001"));
        repository.save(productInfo);
        List<Instrument> result = (List<Instrument>) repository.findAll();

        assertTrue(result.size() > 0);
        assertTrue(result.get(0).getInstrumentName().equals("EUR/USD"));

    }
}