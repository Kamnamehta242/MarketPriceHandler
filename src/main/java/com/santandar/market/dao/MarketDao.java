package com.santandar.market.dao;

import com.santandar.market.dynamodb.entities.Instrument;

public interface MarketDao {
    void saveInstrument(Instrument instrument);
}
