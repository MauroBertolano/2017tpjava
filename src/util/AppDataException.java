package util;

import javax.swing.JOptionPane;

import ui.ABMCInternal;

public class AppDataException extends Exception{
	private Throwable innerException;
	private String message;
	private ABMCInternal asd;
	
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public AppDataException(Throwable e, String message){
		this.innerException=e;
		this.setMessage(message);
	}

}