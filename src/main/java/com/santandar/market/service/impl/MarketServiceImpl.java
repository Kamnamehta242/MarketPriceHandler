package com.santandar.market.service.impl;

import com.santandar.market.dao.MarketDao;
import com.santandar.market.dto.FXMessage;
import com.santandar.market.dynamodb.entities.Instrument;
import com.santandar.market.mapper.InstrumentMapper;
import com.santandar.market.processor.MessageProcessor;
import com.santandar.market.service.MarketService;
import com.santandar.market.service.PartnerService;
import com.santandar.market.utilities.CommissionCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class MarketServiceImpl implements MarketService {
    Logger logger = LoggerFactory.getLogger(MarketServiceImpl.class);
    private final MarketDao marketDao;
    private final InstrumentMapper instrumentMapper;
    private final PartnerService partnerService;

    public MarketServiceImpl(MarketDao marketDao, InstrumentMapper instrumentMapper, PartnerService partnerService) {
        this.marketDao = marketDao;
        this.instrumentMapper = instrumentMapper;
        this.partnerService= partnerService;
    }

    @Override
    public void calculateMarketPrice(FXMessage fxMessage) {
        Instrument instrument = instrumentMapper.updateInstrument(fxMessage, new Instrument());
        logger.info("Instrument info to be saved before commission application:{}", instrument);
        marketDao.saveInstrument(instrument);
        logger.info("calculating commission");
        CommissionCalculator.calculateCommission(instrument);
        logger.info("Calling Rest Api service with updated value");
        partnerService.postRequest(fxMessage,instrument, instrumentMapper);
        instrument.setUpdateDt(new Date());
        marketDao.saveInstrument(instrument);
        logger.info("Instrument info to be saved post commission application:{}", instrument);

    }



}
