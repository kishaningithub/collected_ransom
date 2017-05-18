package com.kishan.collectedransom.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BitCoinEventType {
    @JsonProperty("unconfirmed-tx")
    UNCONFIRMED_TX,
    @JsonProperty("new-block")
    NEW_BLOCK,
    @JsonProperty("confirmed-tx")
    CONFIRMED_TX,
    @JsonProperty("tx-confirmation")
    TX_CONFIRMATION,
    @JsonProperty("double-spend-tx")
    DOUBLE_SPEND_TX,
    @JsonProperty("tx-confidence")
    TX_CONFIDENCE;
}
