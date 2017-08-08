package org.vijay.model;


public class Message {
private int id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
private String source;
private String recepient;
private String subject;
private String message;
private String dateTime;


public String getDateTime() {
	return dateTime;
}
public void setDateTime(String dateTime) {
	this.dateTime = dateTime;
}
public String getSource() {
	return source;
}
public void setSource(String source) {
	this.source = source;
}
public String getRecepient() {
	return recepient;
}
public void setRecepient(String recepient) {
	this.recepient = recepient;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}

}
