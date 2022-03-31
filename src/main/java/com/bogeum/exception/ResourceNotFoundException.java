package com.bogeum.exception;

/**
 * @author YJ_Lee
 * 
 * JPA에서 해당 entity를 찾지 못하였을 경우 던지는 예외
 * Service 단에서 구현하는 것을 추천
 */

public class ResourceNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
