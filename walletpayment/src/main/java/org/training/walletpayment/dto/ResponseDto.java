package org.training.walletpayment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {

	private long responseCode;
	private String message;

	public ResponseDto(long responseCode, String message) {
		super();
		this.responseCode = responseCode;
		this.message = message;
	}

	public ResponseDto() {
		super();
	}

}
