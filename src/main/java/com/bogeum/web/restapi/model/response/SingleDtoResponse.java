package com.bogeum.web.restapi.model.response;

import com.bogeum.web.restapi.model.ApiStatus;

import lombok.Getter;

@Getter
public class SingleDtoResponse<T> extends CommonResponse {
	
	private T dto;
	
	public SingleDtoResponse(ApiStatus apiResponseCode, T dto) {
		super(apiResponseCode);
		this.dto = dto;
	}
}
