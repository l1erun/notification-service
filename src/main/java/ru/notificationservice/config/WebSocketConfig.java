package ru.notificationservice.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.*;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/queue"); // Префикс для сообщений клиенту
        config.setApplicationDestinationPrefixes("/app"); // Префикс для сообщений от клиента
        config.setUserDestinationPrefix("/user"); // Префикс для личных сообщений
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/notifications")
                .setAllowedOriginPatterns("*")
                .withSockJS(); // Поддержка SockJS
    }
}
