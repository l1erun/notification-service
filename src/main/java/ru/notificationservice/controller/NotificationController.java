package ru.notificationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.notificationservice.entity.Notification;
import ru.notificationservice.service.NotificationService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

/**
 * REST-контроллер для управления уведомлениями.
 */
@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    /**
     * Получает список уведомлений текущего пользователя.
     */
    @GetMapping
    public List<Notification> getMyNotifications(Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        return notificationService.getUserNotifications(userId);
    }

    /**
     * Отмечает уведомление как прочитанное.
     */
    @PutMapping("/{notificationId}/read")
    public void markAsRead(@PathVariable UUID notificationId) {
        notificationService.markAsRead(notificationId);
    }
}
