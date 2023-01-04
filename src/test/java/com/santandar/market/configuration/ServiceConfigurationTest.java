package com.santandar.market.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santandar.market.dao.MarketDao;
import com.santandar.market.dynamodb.repositories.InstrumentRepository;
import com.santandar.market.mapper.InstrumentMapper;
import com.santandar.market.processor.BaseMessageProcessor;
import com.santandar.market.service.MarketService;
import com.santandar.market.service.PartnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ServiceConfigurationTest {
    @InjectMocks
    ServiceConfiguration serviceConfiguration;

    @Mock
    InstrumentRepository instrumentRepository;

    @Mock
    ObjectMapper objectMapper;

    @Mock
    InstrumentMapper instrumentMapper;

    @Test
    public void testServiceConfiguration(){
        MarketDao marketDao= serviceConfiguration.getMarketDao(instrumentRepository);
        RestTemplate restTemplate= serviceConfiguration.getRestTemplate();
        PartnerService partnerService= serviceConfiguration.getPartnerService(objectMapper,restTemplate);
        MarketService marketService = serviceConfiguration.getMarketService(marketDao,instrumentMapper,partnerService);
        BaseMessageProcessor baseMessageProcessor = serviceConfiguration.getMessageProcessor(marketService);
        assertTrue(baseMessageProcessor instanceof  BaseMessageProcessor);
    }
}