package dev.newbcoder_f5.ncf5notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import dev.newbcoder_f5.ncf5notificationservice.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class NCf5NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NCf5NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "notification")
    public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
        //@ send out an email notification
        log.info("received notification for order - {}", orderPlacedEvent.getOrderNumber());
        log.info("sent out email 📧");        
        
    }

}
