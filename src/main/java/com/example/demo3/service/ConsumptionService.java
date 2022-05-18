package com.example.demo3.service;

import com.example.demo3.domain.MetricReading;
import com.example.demo3.domain.StatisticsReading;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsumptionService {
    public void  calculateWeekConsumption(List<MetricReading> metricReadings, StatisticsReading statisticsReading) {
        DoubleSummaryStatistics doubleSummaryStatistics = metricReadings.stream().mapToDouble(x-> Double.parseDouble((x.getTemperatureReading()))).summaryStatistics();

        statisticsReading.setMaxReading(String.valueOf(doubleSummaryStatistics.getMax()));
        statisticsReading.setMinReading(String.valueOf(doubleSummaryStatistics.getMin()));
        statisticsReading.setAvgReading((String.valueOf(doubleSummaryStatistics.getAverage())));
        statisticsReading.setSumReading(String.valueOf(doubleSummaryStatistics.getSum()));

    }

    public List<MetricReading> getLatestMetricPerSensor(List<MetricReading> metricReadings) {
      return   metricReadings.stream().sorted(Comparator.comparing( (MetricReading mr) -> mr.getId() ).reversed()).collect(Collectors.toList());

    }
}
