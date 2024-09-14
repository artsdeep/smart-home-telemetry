package ru.yandex.practicum.smarthome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.smarthome.entity.Telemetry;
import ru.yandex.practicum.smarthome.repository.TelemetryRepository;

import java.util.Date;
import java.util.List;

@Service
public class TelemetryService {
    @Autowired
    private TelemetryRepository telemetryRepository;

    public List<Telemetry> getTelemetryByDeviceId(int deviceId) {
        return telemetryRepository.findByDeviceId(deviceId);
    }
    public Telemetry addRegisterTelemetry(String deviceId) {
        Telemetry telemetry = new Telemetry();
        telemetry.setDeviceId(Integer.parseInt(deviceId));
        telemetry.setMetricName("REGISTER");
        telemetry.setValue(1.0);
        telemetry.setTimestamp(new Date());
        return telemetryRepository.save(telemetry);
    }
    public Telemetry addTelemetry(Telemetry telemetry) {
        return telemetryRepository.save(telemetry);
    }
}
