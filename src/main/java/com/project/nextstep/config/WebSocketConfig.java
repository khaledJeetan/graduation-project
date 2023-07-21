package com.project.nextstep.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@EnableConfigurationProperties(NotificationProperties.class)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final NotificationProperties props;

    @Autowired
    public WebSocketConfig(NotificationProperties props) {
        this.props = props;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(props.getEndpoint());
        registry.addEndpoint(props.getEndpoint()).withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(props.getBroker());
        registry.setApplicationDestinationPrefixes(props.getApp());
    }

}
