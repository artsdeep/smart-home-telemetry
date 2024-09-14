package ru.yandex.practicum.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.smarthome.entity.Telemetry;
import ru.yandex.practicum.smarthome.service.KafkaService;
import ru.yandex.practicum.smarthome.service.TelemetryService;


import java.sql.DataTruncation;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/telemetry")
public class TelemetryController {
    @Autowired
    private TelemetryService telemetryService;
    private final KafkaService kafkaService;

    public TelemetryController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @GetMapping("/device/{deviceId}")
    public List<Telemetry> getTelemetryByDeviceId(@PathVariable int deviceId) {
        return telemetryService.getTelemetryByDeviceId(deviceId);
    }
    @GetMapping("/start/{deviceId}")
    public String startTelemetryForDeviceId(@PathVariable int deviceId) {
        Telemetry telemetry = new Telemetry();
        telemetry.setDeviceId(deviceId);
        telemetry.setMetricName("ON");
        telemetry.setValue(1.0);
        telemetry.setTimestamp(new Date());
        telemetryService.addTelemetry(telemetry);
        kafkaService.sendMessage("telemetry-start-device", String.valueOf(deviceId), telemetry.getMetricName()+"=" +telemetry.getValue());
        return "start"+deviceId;
    }
    @PostMapping
    public Telemetry addTelemetry(@RequestBody Telemetry telemetry) {
        kafkaService.sendMessage("telemetry-start-device", telemetry.getDeviceId().toString(), telemetry.getMetricName()+"=" +telemetry.getValue());
        telemetry.setTimestamp(new Date());
        return telemetryService.addTelemetry(telemetry);
    }
}
