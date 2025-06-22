package com.example.financedata.controller;

import com.example.financedata.scheduler.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scheduler")
public class SchedulerController {

    private final ScheduleService scheduleService;

    public SchedulerController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Operation(summary = "Schedule a job to collect data")
    @PostMapping("/schedule")
    public void schedule(@RequestParam @NotBlank String jobName,
                         @RequestParam @NotBlank String isin,
                         @RequestParam @Positive long periodMs) {
        scheduleService.schedule(jobName, isin, periodMs);
    }

    @Operation(summary = "Pause a scheduled job")
    @PostMapping("/pause/{jobName}")
    public void pause(@PathVariable String jobName) {
        scheduleService.pause(jobName);
    }

    @Operation(summary = "Unschedule a job")
    @DeleteMapping("/{jobName}")
    public void unschedule(@PathVariable String jobName) {
        scheduleService.unschedule(jobName);
    }
}
