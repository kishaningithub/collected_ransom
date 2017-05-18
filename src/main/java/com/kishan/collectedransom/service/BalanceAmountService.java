package com.kishan.collectedransom.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

public class BalanceAmountService {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public BigInteger getTotalBalance() throws IOException {
        Request request = new Request.Builder()
                .url("https://blockchain.info/balance?active=13AM4VW2dhxYgXeQepoHkHSQuy6NgaEb94|12t9YDPgwueZ9NyMgw519p7AA8isjr6SMw|115p7UMMngoj1pMvkpHijcRdfJNXj6LrLn")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        String json = response.body().string();

        Map<String, Map<String, Object>> map = mapper.readValue(json, new TypeReference<Map<String, Map<String, Object>>>() {
        });

        BigInteger total = map.values().stream().map(wallet -> String.valueOf(wallet.get("final_balance"))).map(BigInteger::new).reduce(BigInteger.ZERO, BigInteger::add);

        return total;
    }
}
