package com.santandar.market.dto;

import com.santandar.market.utilities.FXDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
public class FXMessage {
    private String id;
    private String instrumentName;
    private double bid;
    private double ask;
    private Date timestamp;
}
