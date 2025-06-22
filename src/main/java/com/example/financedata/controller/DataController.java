package com.example.financedata.controller;

import com.example.financedata.entity.HistoricalData;
import com.example.financedata.service.HistoricalDataService;
import com.example.financedata.service.YahooFinanceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data")
@Validated
public class DataController {

    private final YahooFinanceService yahooFinanceService;
    private final HistoricalDataService historicalDataService;

    public DataController(YahooFinanceService yahooFinanceService,
                          HistoricalDataService historicalDataService) {
        this.yahooFinanceService = yahooFinanceService;
        this.historicalDataService = historicalDataService;
    }

    @Operation(summary = "Collect history for ISIN and save")
    @PostMapping("/collect/{isin}")
    public ResponseEntity<List<HistoricalData>> collect(@PathVariable String isin) {
        List<HistoricalData> data = yahooFinanceService.fetchHistory(isin);
        List<HistoricalData> saved = historicalDataService.saveAll(data);
        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "Get all collected data")
    @GetMapping
    public List<HistoricalData> all() {
        return historicalDataService.findAll();
    }
}
