package com.bogeum.web.restapi.model.response;

import com.bogeum.web.restapi.model.ApiResponseCode;

import lombok.Getter;

@Getter
public class SingleDtoResponse<T> extends CommonResponse {
	
	private T dto;
	
	public SingleDtoResponse(ApiResponseCode apiResponseCode, T dto) {
		super(apiResponseCode);
		this.dto = dto;
	}
}
