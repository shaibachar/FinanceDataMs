package com.example.financedata.scheduler;

import com.example.financedata.service.HistoricalDataService;
import com.example.financedata.service.YahooFinanceService;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduleService {

    private final TaskScheduler taskScheduler = taskScheduler();
    private final YahooFinanceService yahooFinanceService;
    private final HistoricalDataService historicalDataService;
    private final Map<String, ScheduledFuture<?>> jobs = new ConcurrentHashMap<>();

    public ScheduleService(YahooFinanceService yahooFinanceService,
                           HistoricalDataService historicalDataService) {
        this.yahooFinanceService = yahooFinanceService;
        this.historicalDataService = historicalDataService;
    }

    @PostConstruct
    public void init() {
    }

    private TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(2);
        scheduler.initialize();
        return scheduler;
    }

    public void schedule(String jobName, String isin, long periodMs) {
        Runnable task = () -> {
            var data = yahooFinanceService.fetchHistory(isin);
            historicalDataService.saveAll(data);
        };
        ScheduledFuture<?> future = taskScheduler.scheduleAtFixedRate(task, Instant.now(), periodMs);
        jobs.put(jobName, future);
    }

    public void pause(String jobName) {
        ScheduledFuture<?> future = jobs.get(jobName);
        if (future != null) {
            future.cancel(false);
        }
    }

    public void unschedule(String jobName) {
        pause(jobName);
        jobs.remove(jobName);
    }
}
