package com.javaguides.stockservice.kafka;

import com.javaguides.basedomains.dto.OrderEvent;
import com.javaguides.stockservice.entity.StockData;
import com.javaguides.stockservice.repository.StockDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    private StockDataRepository dataRepository;

    public OrderConsumer(StockDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent) {
        LOGGER.info("Consuming message from the Kafka Topics ===============> {}", orderEvent.toString());

        //Save the order event data to the database
        StockData data = new StockData();
        data.setOrder_val(String.valueOf(orderEvent.getOrder()));
        data.setMessage(orderEvent.getMessage());
        data.setStatus_val(orderEvent.getStatus());
        dataRepository.save(data);
    }
}
