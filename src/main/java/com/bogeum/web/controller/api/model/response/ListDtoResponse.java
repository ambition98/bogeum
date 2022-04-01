package com.bogeum.web.controller.api.model.response;

import java.util.List;

import com.bogeum.web.controller.api.model.ApiStatus;

import lombok.Getter;

@Getter
public class ListDtoResponse<T> extends CommonResponse {
	
	private final List<T> dtoList;
	
	public ListDtoResponse(ApiStatus apiResponseCode, List<T> dtoList) {
		super(apiResponseCode);
		this.dtoList = dtoList;
	}
}
