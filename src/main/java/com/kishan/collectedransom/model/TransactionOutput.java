package com.kishan.collectedransom.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionOutput {

    @Getter
    private BigInteger value;
    private List<String> addresses;

    public List<BitCoinAddress> getAddresses() {
        return addresses.stream().map(BitCoinAddress::new).collect(Collectors.toList());
    }

}
