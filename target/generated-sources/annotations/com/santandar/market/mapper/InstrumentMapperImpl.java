package com.santandar.market.mapper;

import com.santandar.market.dto.FXMessage;
import com.santandar.market.dynamodb.entities.Instrument;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-31T01:33:58+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.6 (JetBrains s.r.o)"
)
@Component
public class InstrumentMapperImpl implements InstrumentMapper {

    @Override
    public Instrument updateInstrument(FXMessage fxMessage, Instrument instrument) {
        if ( fxMessage == null ) {
            return instrument;
        }

        instrument.setId( fxMessage.getId() );
        instrument.setInstrumentName( fxMessage.getInstrumentName() );
        instrument.setBid( fxMessage.getBid() );
        instrument.setAsk( fxMessage.getAsk() );
        instrument.setTimestamp( fxMessage.getTimestamp() );

        return instrument;
    }

    @Override
    public FXMessage updateFXMessage(Instrument instrument, FXMessage fxMessage) {
        if ( instrument == null ) {
            return fxMessage;
        }

        fxMessage.setBid( instrument.getUpdatedBid() );
        fxMessage.setAsk( instrument.getUpdatedAsk() );
        fxMessage.setId( instrument.getId() );
        fxMessage.setInstrumentName( instrument.getInstrumentName() );
        fxMessage.setTimestamp( instrument.getTimestamp() );

        return fxMessage;
    }
}
