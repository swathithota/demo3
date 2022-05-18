package com.example.demo3.service;

import com.example.demo3.domain.MetricReading;
import com.example.demo3.domain.RegisterMSG;
import com.example.demo3.repo.MetricRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo3.constants.ApplicationConstants.SUCCESSMSG;

@Service
public class MetricService {

    @Autowired
    MetricRepo metricRepo ;

    public void storeMetric(MetricReading reading) {

        metricRepo.save(reading);

    }

    public List<MetricReading> getDetails() {

        return metricRepo.findAll();

    }

    public List<MetricReading> getDetailsBySensor(String sensorID) {

        return metricRepo.findAllBySensorId(sensorID);
    }

    public void setDetails(RegisterMSG registerMSG, MetricReading reading) {

        registerMSG.setMessage(SUCCESSMSG);
        registerMSG.setGeneratedId(String.valueOf(reading.getId()));
        registerMSG.setSensorId(reading.getSensorId());
    }
}
