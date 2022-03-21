package com.bogeum.web.restapi.model.response;

import com.bogeum.web.restapi.model.ApiResponseCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse {
	
	private ApiResponseCode apiResponseCode;
	
	public CommonResponse(ApiResponseCode apiResponseCode) {
		this.apiResponseCode = apiResponseCode;
	}
}
