package Exceptions;

@SuppressWarnings("serial")
public class WarehouseDataException extends CompanyException{
	public WarehouseDataException() {
    }

    public WarehouseDataException(String message) {
        super(message);
    }
}
