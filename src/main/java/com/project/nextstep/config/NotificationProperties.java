package com.project.nextstep.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "notification.ws")
public class NotificationProperties {
    private String app = "/notification-ws";
    private String broker = "/topic";
    private String endpoint = "/ws";

}
