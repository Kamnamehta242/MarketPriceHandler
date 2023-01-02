package com.santandar.market.utilities;

import com.santandar.market.dto.FXMessage;
import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class MessageBuilder {

   public FXMessage buildMessage(String input) throws ParseException {
        List<String> messageList = Stream.of(input.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        return FXMessage.builder().id(messageList.get(0)).instrumentName(messageList.get(1))
                .bid(Double.valueOf(messageList.get(2))).ask(Double.valueOf(messageList.get(3)))
                .timestamp(FXDateTime.convertToDate(messageList.get(4))).build();
    }

}
