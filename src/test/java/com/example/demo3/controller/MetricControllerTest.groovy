package com.example.demo3.controller

import com.example.demo3.domain.MetricReading

import com.example.demo3.service.MetricService
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class MetricControllerTest extends Specification{

     MetricService metricService = Mock();
     MetricController metricController = new MetricController();

    def "Register valid id" (){
        given:

        MetricReading reading = new MetricReading()

        reading.setSensorId("sensor_1")
        reading.setTemperatureReading("32.3")

        metricController.metricService = metricService

        when:

        ResponseEntity response = metricController.register(reading)

        then:
         response.statusCodeValue == 200

    }

    def "Register valid id not vaild" (){
        given:

        MetricReading reading = new MetricReading()


        metricController.metricService = metricService

        when:

        ResponseEntity response = metricController.register(reading)

        then:
        response.statusCodeValue == 400

    }

}
