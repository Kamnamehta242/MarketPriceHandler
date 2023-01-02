package com.santandar.market.utilities;

import com.santandar.market.dynamodb.entities.Instrument;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommissionCalculator {
    /**
     * Hardcoded will be fetched from commssion table on some Id basis
     **/
    private double bidCommisionValue = -0.1;
    private double askCommisionValue = 0.1;

    public void calculateCommission(Instrument instrument) {
        instrument.setUpdatedBid(instrument.getBid() + instrument.getBid() * bidCommisionValue / 100);
        instrument.setUpdatedAsk(instrument.getBid() + instrument.getBid() * askCommisionValue / 100);
    }

}
