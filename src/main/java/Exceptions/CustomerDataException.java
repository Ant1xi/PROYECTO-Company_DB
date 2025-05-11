package Exceptions;

@SuppressWarnings("serial")
public class CustomerDataException extends CompanyException{
	public CustomerDataException() {
    }

    public CustomerDataException(String message) {
        super(message);
    }
}
