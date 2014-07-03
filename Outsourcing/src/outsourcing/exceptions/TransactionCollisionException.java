package outsourcing.exceptions;

public class TransactionCollisionException extends Exception{
	public TransactionCollisionException(String name){
		super(name);
	}
}
