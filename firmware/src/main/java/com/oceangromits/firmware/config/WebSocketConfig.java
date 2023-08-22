package com.oceangromits.firmware.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { //sets endpoints for the rtc connection using SockJS
        registry.addEndpoint("/signaller").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) { //configures the message registry with the webrtc
        registry.setApplicationDestinationPrefixes("/webrtc");
        registry.enableSimpleBroker("/msg", "/queue");
    }



}
