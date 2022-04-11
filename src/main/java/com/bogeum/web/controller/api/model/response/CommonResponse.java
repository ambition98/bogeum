package com.bogeum.web.controller.api.model.response;

import com.bogeum.web.controller.api.model.ApiStatus;
import lombok.Getter;

@Getter
public class CommonResponse {
	
	private final ApiStatus apiStatus;

	public CommonResponse(ApiStatus apiStatus) {
		this.apiStatus = apiStatus;
	}
}
