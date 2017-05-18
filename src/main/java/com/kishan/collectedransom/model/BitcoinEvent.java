package com.kishan.collectedransom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class BitcoinEvent {
    private String id;
    private BitCoinEventType event;
    private String address;
}
