package com.kishan.collectedransom.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kishan.collectedransom.model.BitCoinAddress;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.joda.money.CurrencyUnit;

import javax.inject.Inject;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BitCoinService {

    private final OkHttpClient client;
    private final ObjectMapper mapper;

    @Inject
    public BitCoinService(OkHttpClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    public BigInteger getTotalBalance(List<BitCoinAddress> bitCoinAddresses) throws IOException {
        String addressStr = bitCoinAddresses.stream().map(BitCoinAddress::getAddress).collect(Collectors.joining("|"));
        Request request = new Request.Builder()
                .url("https://blockchain.info/balance?active=" + addressStr)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        String json = response.body().string();

        Map<String, Map<String, Object>> map = mapper.readValue(json, new TypeReference<Map<String, Map<String, Object>>>() {
        });

        return map.values().stream().map(wallet -> String.valueOf(wallet.get("final_balance"))).map(BigInteger::new).reduce(BigInteger.ZERO, BigInteger::add);
    }

    public BigDecimal getINRValue(BigDecimal btcAmount) throws IOException {
        BigDecimal btcToInrConversionRate = getBitCoinConversionRate(CurrencyUnit.of("INR"));
        return btcAmount.multiply(btcToInrConversionRate).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getBTC(BigInteger noOfBitCoinUnits) {
        long bitCoinUnits = 100000000L;
        int roundingScale = 8;
        return  new BigDecimal(noOfBitCoinUnits).divide(new BigDecimal(bitCoinUnits), roundingScale, RoundingMode.UNNECESSARY);
    }

    private BigDecimal getBitCoinConversionRate(CurrencyUnit currencyUnit) throws IOException {
        Request request = new Request.Builder()
                .url("https://bitpay.com/api/rates/" + currencyUnit.getCurrencyCode())
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        String json = response.body().string();

        Map<String, Object> map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
        });

        return new BigDecimal(String.valueOf(map.get("rate")));
    }
}
