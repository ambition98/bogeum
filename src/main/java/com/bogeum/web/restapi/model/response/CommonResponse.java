package com.bogeum.web.restapi.model.response;

import com.bogeum.web.restapi.model.ApiStatus;

import lombok.Getter;

@Getter
public class CommonResponse {
	
	private ApiStatus apiResponseCode;
	
	public CommonResponse(ApiStatus apiResponseCode) {
		this.apiResponseCode = apiResponseCode;
	}
}
