package ru.yandex.practicum.smarthome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.smarthome.entity.Telemetry;

@Service
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate; // объявляем final только если это необходимо
    @Autowired
    private TelemetryService telemetryService;
    @Autowired
    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate; // используем конструктор для инъекции зависимостей
    }

    public void sendMessage(String topic, String key, String message) {
        kafkaTemplate.send(topic, key, message);
    }
    @KafkaListener(topics = "register-device", groupId = "smart_group_id")
    public void listen(String message) {
        telemetryService.addRegisterTelemetry(message);
        // Здесь вы можете добавить логику обработки сообщения
    }
    @KafkaListener(topics = "telemetry_topic", groupId = "smart_group_id")
    public void listen(Telemetry telemetry) {
        System.out.println("Received Message: " + telemetry.getMetricName());
        // Здесь вы можете добавить логику обработки сообщения
    }

}
