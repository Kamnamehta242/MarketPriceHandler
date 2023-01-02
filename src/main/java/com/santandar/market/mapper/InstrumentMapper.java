package com.santandar.market.mapper;

import com.santandar.market.dto.FXMessage;
import com.santandar.market.dynamodb.entities.Instrument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface InstrumentMapper {

    Instrument updateInstrument(FXMessage fxMessage, @MappingTarget Instrument instrument);

    @Mapping(target="bid", source = "updatedBid")
    @Mapping(target="ask", source = "updatedAsk")
    FXMessage updateFXMessage( Instrument instrument, @MappingTarget FXMessage fxMessage);
}