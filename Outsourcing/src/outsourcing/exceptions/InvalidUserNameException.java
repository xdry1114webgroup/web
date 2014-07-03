package outsourcing.exceptions;

public class InvalidUserNameException extends Exception{
	public InvalidUserNameException(String name){
		super(name);
	}
}
