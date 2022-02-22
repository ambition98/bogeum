package com.bogeum.web.restapi.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleDtoResponse<T> extends CommonResponse {
	
	private T dto;
}
