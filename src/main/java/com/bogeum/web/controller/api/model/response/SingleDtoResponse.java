package com.bogeum.web.controller.api.model.response;

import com.bogeum.web.controller.api.model.ApiStatus;

import lombok.Getter;

@Getter
public class SingleDtoResponse<T> extends CommonResponse {
	
	private final T dto;
	
	public SingleDtoResponse(ApiStatus apiResponseCode, T dto) {
		super(apiResponseCode);
		this.dto = dto;
	}
}
