package com.kishan.collectedransom.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    List<TransactionOutput> outputs;

    public BigInteger getTotalReceived(List<BitCoinAddress> ransomWareDestinationAddresses) {
        return outputs.stream().filter(txnOp -> {
            List<BitCoinAddress> intersection = new ArrayList<>();
            intersection.addAll(txnOp.getAddresses());
            intersection.retainAll(ransomWareDestinationAddresses);
            return !intersection.isEmpty();
        }).map(TransactionOutput::getValue).reduce(BigInteger::add).orElse(BigInteger.ZERO);
    }
}
