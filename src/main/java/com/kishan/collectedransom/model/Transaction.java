package com.kishan.collectedransom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Transaction {
    private BigInteger total;
}
