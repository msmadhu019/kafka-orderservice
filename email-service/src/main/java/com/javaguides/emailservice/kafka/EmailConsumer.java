package com.javaguides.emailservice.kafka;

import com.javaguides.basedomains.dto.OrderEvent;
import com.javaguides.emailservice.entity.EmailData;
import com.javaguides.emailservice.repository.EmailDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailConsumer.class);

    private EmailDataRepository dataRepository;

    public EmailConsumer(EmailDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent) {
        LOGGER.info("Consuming message from the Kafka Topics ===============> {}", orderEvent.toString());

        //Save the order event data to the database
        EmailData data = new EmailData();
        data.setEmail_val(String.valueOf(orderEvent.getOrder().getEmail()));
        data.setName_val(orderEvent.getOrder().getName());
        dataRepository.save(data);
    }
}
