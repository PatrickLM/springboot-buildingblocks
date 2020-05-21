package com.learnrestservice.exceptions;

import java.util.Date;

public class CustomErrorDetails {
private String message;
private String errordetails;
private Date timestamp;

public CustomErrorDetails(String message, String errordetails, Date timestamp) {
	this.message = message;
	this.errordetails = errordetails;
	this.timestamp = timestamp;
}

public String getMessage() {
	return message;
}

public String getErrordetails() {
	return errordetails;
}

public Date getTimestamp() {
	return timestamp;
}



}
