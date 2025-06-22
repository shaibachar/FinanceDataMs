# FinanceDataMs

Spring Boot microservice to collect historical stock and bond data from Yahoo Finance. 

## Features

* REST API documented with Swagger UI
* Upload CSV files for markets (containing ISINs)
* Trigger data collection manually or via scheduled jobs
* Manage scheduled jobs with endpoints
* Data stored in in-memory H2 database

## Running

```bash
mvn spring-boot:run
```

Swagger UI available at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
