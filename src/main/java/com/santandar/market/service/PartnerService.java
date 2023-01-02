package com.santandar.market.service;

import com.santandar.market.dto.FXMessage;
import com.santandar.market.dynamodb.entities.Instrument;
import com.santandar.market.mapper.InstrumentMapper;

public interface PartnerService {
    void postRequest(FXMessage fxMessage, Instrument instrument, InstrumentMapper instrumentMapper);
}
