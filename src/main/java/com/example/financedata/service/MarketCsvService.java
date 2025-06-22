package com.example.financedata.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MarketCsvService {

    private final Map<String, String> csvStorage = new ConcurrentHashMap<>();

    public void save(String market, MultipartFile file) throws IOException {
        csvStorage.put(market, new String(file.getBytes()));
    }

    public String get(String market) {
        return csvStorage.get(market);
    }

    public Map<String, String> all() {
        return csvStorage;
    }

    public void delete(String market) {
        csvStorage.remove(market);
    }
}
