package com.bogeum.web.restapi.model.response;

import java.util.List;

import com.bogeum.web.restapi.model.ApiResponseCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListDtoResponse<T> extends CommonResponse {
	
	private List<T> dtoList;
	
	public ListDtoResponse(ApiResponseCode apiResponseCode, List<T> dtoList) {
		super(apiResponseCode);
		this.dtoList = dtoList;
	}
}
