package com.bogeum.web.controller.api.model.response;

import com.bogeum.web.controller.api.model.ApiStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class ListDtoResponse<T> extends CommonResponse {
	
	private final List<T> dtoList;
	private final int size;

	public ListDtoResponse(ApiStatus apiResponseCode, List<T> dtoList) {
		super(apiResponseCode);
		this.dtoList = dtoList;
		this.size = dtoList.size();
	}
}
