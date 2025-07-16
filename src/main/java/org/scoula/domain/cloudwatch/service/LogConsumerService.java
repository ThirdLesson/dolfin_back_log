package org.scoula.domain.cloudwatch.service;

import org.springframework.stereotype.Service;

@Service
public interface LogConsumerService {

	void loggingIntegration(String message) throws Exception;
}
