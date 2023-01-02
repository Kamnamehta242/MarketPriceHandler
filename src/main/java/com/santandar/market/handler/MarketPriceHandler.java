package com.santandar.market.handler;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.santandar.market.dto.FXMessage;
import com.santandar.market.processor.BaseMessageProcessor;
import com.santandar.market.processor.MessageProcessor;
import com.santandar.market.utilities.FXDateTime;
import com.santandar.market.utilities.MessageBuilder;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarketPriceHandler implements RequestHandler<String, Object> {
    Logger logger = LoggerFactory.getLogger(MarketPriceHandler.class);
    private final BaseMessageProcessor messageProcessor;

    @Autowired
    public MarketPriceHandler(final BaseMessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    @SneakyThrows
    @Override
    public Object handleRequest(String input, Context context) {
        logger.info("Event received in Handler ");
        try {
            FXMessage fxMessage = MessageBuilder.buildMessage(input);
            messageProcessor.processMessage(fxMessage);
        } catch (Exception ex) {
            logger.error("Exception ",ex.getMessage());
        }
        return "Lambda Function is invoked...." + input;
    }
}

