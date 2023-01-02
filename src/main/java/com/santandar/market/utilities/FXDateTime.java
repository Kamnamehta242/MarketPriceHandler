package com.santandar.market.utilities;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
public class FXDateTime {

    public Date convertToDate(String receivedDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss.SSS");
        Date date = formatter.parse(receivedDate);
        return date;
    }

    public String convertToDateString(Date receivedDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss.SSS");
        String strDate = formatter.format(receivedDate);
        return strDate;
    }


}
