package com.bogeum.web.restapi.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse {
	
	private boolean success;
	private int code;
	private String msg;
}
