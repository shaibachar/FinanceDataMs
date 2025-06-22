package com.example.financedata.service;

import com.example.financedata.entity.HistoricalData;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Collections;

@Service
public class YahooFinanceService {

    public List<HistoricalData> fetchHistory(String isin) {
        // Placeholder: actual implementation should call Yahoo Finance API
        HistoricalData data = new HistoricalData();
        data.setIsin(isin);
        data.setDate(LocalDate.now());
        data.setOpenPrice(0.0);
        data.setHighPrice(0.0);
        data.setLowPrice(0.0);
        data.setClosePrice(0.0);
        data.setVolume(0L);
        return Collections.singletonList(data);
    }
}
