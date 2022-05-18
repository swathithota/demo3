package com.example.demo3.controller;

import com.example.demo3.domain.MetricReading;
import com.example.demo3.domain.StatisticsReading;
import com.example.demo3.service.ConsumptionService;
import com.example.demo3.service.MetricService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consumption")
public class ConsumptionController {

    private final static Logger LOGGER = LogManager.getLogger(ConsumptionController.class);
    @Autowired
    private MetricService metricService;

    @Autowired
    ConsumptionService consumptionService;

    /*Asumption made that sensorID has seven days data, in general calculates for avg days*/
    @GetMapping("/week/{sensorID}")
    public ResponseEntity calculatePerWeekConsumption(@PathVariable String sensorID){

        StatisticsReading statisticsReading = new StatisticsReading();
        List<MetricReading> metricReadings = metricService.getDetailsBySensor(sensorID);

        if(metricReadings.size() > 0){

            consumptionService.calculateWeekConsumption(metricReadings,statisticsReading);
            return new ResponseEntity(statisticsReading, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }
    @GetMapping("/dateRange/{sensorID}")
    public ResponseEntity calculateDateRangeConsumption(@PathVariable String sensorID){

        List<MetricReading> metricReadings = metricService.getDetailsBySensor(sensorID);

        List<MetricReading> metricReadingsReverse =  consumptionService.getLatestMetricPerSensor(metricReadings);

        if(metricReadingsReverse.size() >0){
            return new ResponseEntity(metricReadingsReverse.get(0),HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }
}
