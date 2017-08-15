package util;

public class PersonaExistente extends Exception{
	private Throwable innerException;
	private String message;
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public PersonaExistente(String message){
		this.setMessage(message);
	}

}
