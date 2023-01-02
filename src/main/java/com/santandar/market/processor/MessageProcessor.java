package com.santandar.market.processor;

import com.santandar.market.dto.FXMessage;
import com.santandar.market.service.MarketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageProcessor implements BaseMessageProcessor {
    Logger logger = LoggerFactory.getLogger(MessageProcessor.class);
    private final MarketService marketService;

    public MessageProcessor(MarketService marketService) {
        this.marketService = marketService;
    }

    @Override
    public void processMessage(FXMessage message) {
        logger.info("Message Received is :{} ", message);
        marketService.calculateMarketPrice(message);
        logger.info("Process Message completed ..");
    }
}
