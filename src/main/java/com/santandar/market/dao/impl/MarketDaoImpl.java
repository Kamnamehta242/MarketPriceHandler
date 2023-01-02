package com.santandar.market.dao.impl;

import com.santandar.market.dao.MarketDao;
import com.santandar.market.dynamodb.entities.Instrument;
import com.santandar.market.dynamodb.repositories.InstrumentRepository;

public class MarketDaoImpl implements MarketDao {

    private final InstrumentRepository instrumentRepository;

    public MarketDaoImpl(final InstrumentRepository instrumentRepository) {

        this.instrumentRepository = instrumentRepository;
    }

    @Override
    public void saveInstrument(Instrument instrument) {
        instrumentRepository.save(instrument);
    }
}
