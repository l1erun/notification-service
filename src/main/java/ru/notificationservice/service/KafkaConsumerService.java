package ru.notificationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.notificationservice.entity.Notification;

import java.util.UUID;

/**
 * Сервис для потребления сообщений из Kafka.
 */
@Service
public class KafkaConsumerService {
    @Autowired
    private NotificationService notificationService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "game-events", groupId = "notification-service-group")
    public void listenGameEvents(ConsumerRecord<String, String> record) {
        try {
            // Предполагается, что сообщение - это JSON строка
            String eventJson = record.value();
            GameEvent event = objectMapper.readValue(eventJson, GameEvent.class);

            // Создание и отправка уведомления на основе события
            Notification notification = new Notification();
            notification.setUserId(event.getUserId());
            notification.setMessage(event.getMessage());
            notification.setType(event.getType());

            notificationService.sendNotification(notification);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Класс для десериализации события
    public static class GameEvent {
        private UUID userId;
        private String message;
        private String type;

        // Геттеры и сеттеры
        public UUID getUserId() { return userId; }
        public void setUserId(UUID userId) { this.userId = userId; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
    }
}
