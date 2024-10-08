package ru.notificationservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Уведомление для пользователя.
 */
@Entity
@Table(name = "notifications")
@Data
public class Notification {
    @Id
    @GeneratedValue
    private UUID id; // Уникальный идентификатор уведомления

    @Column(nullable = false)
    private UUID userId; // Идентификатор получателя

    @Column(nullable = false)
    private String message; // Текст уведомления

    @Column(nullable = false)
    private LocalDateTime timestamp; // Время отправки

    @Column(nullable = false)
    private String type; // Тип уведомления (информация, предупреждение и т.п.)

    @Column(nullable = false)
    private boolean read; // Прочитано ли уведомление
}
