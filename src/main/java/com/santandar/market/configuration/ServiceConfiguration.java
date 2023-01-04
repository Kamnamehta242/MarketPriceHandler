package com.santandar.market.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santandar.market.dao.MarketDao;
import com.santandar.market.dao.impl.MarketDaoImpl;
import com.santandar.market.dynamodb.repositories.InstrumentRepository;
import com.santandar.market.mapper.InstrumentMapper;
import com.santandar.market.processor.BaseMessageProcessor;
import com.santandar.market.processor.MessageProcessor;
import com.santandar.market.service.MarketService;
import com.santandar.market.service.PartnerService;
import com.santandar.market.service.impl.MarketServiceImpl;
import com.santandar.market.service.impl.PartnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceConfiguration {
    @Bean
    BaseMessageProcessor getMessageProcessor(MarketService marketService) {
        return new MessageProcessor(marketService);
    }

    @Bean
    MarketDao getMarketDao(@Autowired InstrumentRepository instrumentRepository) {
        return new MarketDaoImpl(instrumentRepository);
    }

    @Bean
    MarketService getMarketService(MarketDao marketDao, @Autowired InstrumentMapper instrumentMapper, PartnerService partnerService) {
        return new MarketServiceImpl(marketDao, instrumentMapper, partnerService);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    PartnerService getPartnerService(@Autowired ObjectMapper objectMapper, RestTemplate restTemplate) {
        return new PartnerServiceImpl(objectMapper,restTemplate);
    }
}
