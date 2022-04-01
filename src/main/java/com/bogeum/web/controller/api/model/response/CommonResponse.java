package com.bogeum.web.controller.api.model.response;

import com.bogeum.web.controller.api.model.ApiStatus;

import lombok.Getter;

@Getter
public class CommonResponse {
	
	private final ApiStatus apiResponseCode;
	
	public CommonResponse(ApiStatus apiResponseCode) {
		this.apiResponseCode = apiResponseCode;
	}
}
