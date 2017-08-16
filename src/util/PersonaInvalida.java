package util;

public class PersonaInvalida extends Exception{
	private Throwable innerException;
	private String message;
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public PersonaInvalida(String message){
		this.setMessage(message);
	}

}
