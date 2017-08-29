package com.mywjdh.logistics.logisticswechat.exception;

public class RequestLimitException extends RuntimeException {

    public RequestLimitException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestLimitException(String message){
        super(message);
    }

    public RequestLimitException(String message, Throwable cause){
        super(message, cause);
    }
}