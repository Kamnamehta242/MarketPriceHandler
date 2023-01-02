package com.santandar.market.processor;

import com.santandar.market.dto.FXMessage;

public interface BaseMessageProcessor {
    void processMessage(FXMessage message);
}
