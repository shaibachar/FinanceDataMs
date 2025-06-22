package com.example.financedata.controller;

import com.example.financedata.service.MarketCsvService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/market-csv")
public class MarketCsvController {

    private final MarketCsvService marketCsvService;

    public MarketCsvController(MarketCsvService marketCsvService) {
        this.marketCsvService = marketCsvService;
    }

    @Operation(summary = "Upload market CSV file")
    @PostMapping(value = "/{market}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(@PathVariable String market, @RequestPart("file") MultipartFile file) throws Exception {
        marketCsvService.save(market, file);
    }

    @Operation(summary = "Get CSV for market")
    @GetMapping(value = "/{market}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> get(@PathVariable String market) {
        String csv = marketCsvService.get(market);
        if (csv == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(csv);
    }

    @Operation(summary = "Delete CSV for market")
    @DeleteMapping("/{market}")
    public void delete(@PathVariable String market) {
        marketCsvService.delete(market);
    }
}
