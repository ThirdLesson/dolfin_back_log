package org.scoula.domain.cloudwatch.dto;

import lombok.Data;

@Data
public class LogMessage {
	private String logLevel;
	private String timestamp;
	private String callApiPath;
	private String apiMethod;
	private String txId;
	private String srcIp;
	private String deviceInfo;
	private String memberId;
	private String requestId;
	private String ledgerCode;
	private String errorMessage;
}
