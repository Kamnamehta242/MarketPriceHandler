package com.santandar.market.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santandar.market.dto.FXMessage;
import com.santandar.market.dynamodb.entities.Instrument;
import com.santandar.market.mapper.InstrumentMapper;
import com.santandar.market.service.PartnerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


public class PartnerServiceImpl implements PartnerService {
    Logger logger = LoggerFactory.getLogger(PartnerServiceImpl.class);

    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;

    public PartnerServiceImpl(ObjectMapper objectMapper, RestTemplate restTemplate) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    /**
     * we can also push it as an event. Assuming  its not async. using rest template
     **/
    @Override
    public void postRequest(FXMessage fxMessage, Instrument instrument, InstrumentMapper instrumentMapper) {
        FXMessage fxMessage1 = instrumentMapper.updateFXMessage(instrument, fxMessage);
        logger.info("Updated data sent to the APi :{}", fxMessage1);
        callPartner(fxMessage1);

    }

    private void callPartner(FXMessage fxMessage) {
        // 1. Producer application URL
        try {
            String url = "http://localhost:8080/fx";
            // Send JSON data as Body

            String body = objectMapper.writeValueAsString(fxMessage);
            System.out.println("ResultingJSONstring = " + body);
            //System.out.println(json);

            // Http Header
            HttpHeaders headers = new HttpHeaders();
            //Set Content Type
            headers.setContentType(MediaType.APPLICATION_JSON);
            //requestEntity : Body+Header
            HttpEntity<String> request = new HttpEntity<String>(body, headers);
            // 2. make HTTP call and store Response (URL,ResponseType)
            //	ResponseEntity<String> response =  restTemplate.postForEntity(url, request, String.class);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            // 3. Print details(body,status..etc)
            logger.info("Response Body : {}", response.getBody());
            logger.info("Status code value : {}", response.getStatusCodeValue());
            logger.info("Status code : {}", response.getStatusCode().name());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }
}
