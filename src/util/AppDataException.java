package util;

public class AppDataException extends Exception{
	private Throwable innerException;
	private String message;
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public AppDataException(String message){
		this.setMessage(message);
	}

}