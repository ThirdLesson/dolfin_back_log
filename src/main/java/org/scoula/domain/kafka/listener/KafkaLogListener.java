package org.scoula.domain.kafka.listener;

import org.scoula.domain.cloudwatch.service.LogConsumerService;
import org.scoula.domain.kafka.producer.KafkaProducer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaLogListener {

	private final LogConsumerService logConsumerService;
	private final KafkaProducer kafkaProducer;

	@KafkaListener(topics = "log-module", groupId = "log-consumer-group")
	public void listen(String message, Acknowledgment ack) {
		try {
			logConsumerService.loggingIntegration(message);
			ack.acknowledge();
		}catch (Exception e) {
			log.error(e.getMessage());
			kafkaProducer.sendToRetryTopic(message);
			ack.acknowledge();
		}
	}

	@KafkaListener(topics = "log-retry", groupId = "log-retry-group")
	public void listenRetry(String message, Acknowledgment ack) {
		try {
			logConsumerService.loggingIntegration(message);
			ack.acknowledge();
		}catch (Exception e) {
			log.error(e.getMessage());
			kafkaProducer.sendToRetryTopic(message);
			ack.acknowledge();
		}
	}
}

