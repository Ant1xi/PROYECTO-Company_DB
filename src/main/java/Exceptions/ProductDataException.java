package Exceptions;

@SuppressWarnings("serial")
public class ProductDataException extends CompanyException{
	public ProductDataException() {
    }

    public ProductDataException(String message) {
        super(message);
    }
}