package com.santandar.market.service;

import com.santandar.market.dto.FXMessage;

public interface MarketService {
    void calculateMarketPrice(FXMessage fxMessage);
}
