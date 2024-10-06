package lab3;

public class Lab3Exception extends Exception {
	public String message;
	public Lab3Exception(String message) {
		this.message = message;
	}
		
	public String getMessage() {
		return message;
	}

}
