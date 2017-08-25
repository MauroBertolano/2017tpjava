package util;

public class ValorInvalido extends Exception{
	private Throwable innerException;
	private String message;
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public ValorInvalido(String message){
		this.setMessage(message);
	}

}
