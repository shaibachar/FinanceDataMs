package com.example.financedata.service;

import com.example.financedata.entity.HistoricalData;
import com.example.financedata.repository.HistoricalDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricalDataService {

    private final HistoricalDataRepository repository;

    public HistoricalDataService(HistoricalDataRepository repository) {
        this.repository = repository;
    }

    public List<HistoricalData> saveAll(List<HistoricalData> data) {
        return repository.saveAll(data);
    }

    public List<HistoricalData> findAll() {
        return repository.findAll();
    }
}
