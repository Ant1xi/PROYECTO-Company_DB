package Exceptions;

public class EmployeeDataException extends CompanyException{
	public EmployeeDataException() {
    }

    public EmployeeDataException(String message) {
        super(message);
    }
}
