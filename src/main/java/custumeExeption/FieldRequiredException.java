package custumeExeption;

public class FieldRequiredException extends RuntimeException {

	public FieldRequiredException(String str) {
		super(str);
	}
	
}
