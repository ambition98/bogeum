package com.bogeum.web.restapi.model.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListDtoResponse<T> extends CommonResponse {
	
	private List<T> dtoList;
}
