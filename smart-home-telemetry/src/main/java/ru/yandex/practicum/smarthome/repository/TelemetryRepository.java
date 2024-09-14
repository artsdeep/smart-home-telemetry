package ru.yandex.practicum.smarthome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.smarthome.entity.*;

import java.sql.Date;
import java.util.List;

@Repository
public interface TelemetryRepository extends JpaRepository<Telemetry, Integer> {
    List<Telemetry> findByDeviceId(Integer deviceId);
    List<Telemetry> findByMetricName(String metricName);
    List<Telemetry> findByDeviceIdAndTimestampBetween(Integer deviceId, Date startDate, Date endDate);
}
