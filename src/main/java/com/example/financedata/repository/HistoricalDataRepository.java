package com.example.financedata.repository;

import com.example.financedata.entity.HistoricalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricalDataRepository extends JpaRepository<HistoricalData, Long> {
}
