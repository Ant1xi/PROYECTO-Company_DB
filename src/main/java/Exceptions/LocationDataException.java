package Exceptions;

@SuppressWarnings("serial")
public class LocationDataException extends CompanyException{
	public LocationDataException() {
    }

    public LocationDataException(String message) {
        super(message);
    }
}
