package com.example.demo3.controller;


import com.example.demo3.domain.MetricReading;
import com.example.demo3.domain.RegisterMSG;
import com.example.demo3.exception.NotValidRequest;
import com.example.demo3.service.MetricService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Optional;

import static com.example.demo3.constants.ApplicationConstants.*;

@RestController
@RequestMapping("/sensor")
public class MetricController {

    private final static Logger LOGGER = LogManager.getLogger(MetricController.class);

    @Autowired
    private MetricService metricService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody MetricReading reading){

        if(!isValidMetric(reading)){
            LOGGER.info(ERROR + reading);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

        metricService.storeMetric(reading);
        LOGGER.info(RECEIVED + reading);
        RegisterMSG registerMSG = new RegisterMSG();

        metricService.setDetails(registerMSG,reading);
        return ResponseEntity.ok(registerMSG);

    }

    @GetMapping("/read/all")
    public ResponseEntity <List<MetricReading>>  getAllDetails (){

        List<MetricReading> metricReadings = metricService.getDetails();

        if(metricReadings.size() >0){
            return ResponseEntity.ok(metricReadings);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/read/{sensorID}")
    public ResponseEntity <List<MetricReading>> getAllDetailsBySensor (@PathVariable String sensorID){

        List<MetricReading> metricReadings = metricService.getDetailsBySensor(sensorID);

        if(metricReadings.size() >0){
            return ResponseEntity.ok(metricReadings);
        }
      ;
        return ResponseEntity.notFound().build();
    }

    private boolean isValidMetric(MetricReading reading) {
        String sensorID = reading.getSensorId();

        return (null!= sensorID && !sensorID.isEmpty());
    }
}
