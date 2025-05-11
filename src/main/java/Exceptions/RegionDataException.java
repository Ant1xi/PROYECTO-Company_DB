package Exceptions;

@SuppressWarnings("serial")
public class RegionDataException extends CompanyException{
	public RegionDataException() {
    }

    public RegionDataException(String message) {
        super(message);
    }
}
