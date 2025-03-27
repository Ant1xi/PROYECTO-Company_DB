package tablas;

import java.util.Date;

public class Employee {
	private Integer employeeId; //Wrapper para poder almacenar nulos
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Date hireDate;
	private Integer managerId;
	private String jobTitle;
	
	public Employee(Integer employeeId, String firstName, String lastName, String email, String phone, Date hireDate,
			Integer managerId, String jobTitle) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.hireDate = hireDate;
		this.managerId = managerId;
		this.jobTitle = jobTitle;
	}
	
	

	public Integer getEmployeeId() {
		return employeeId;
	}



	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public Date getHireDate() {
		return hireDate;
	}



	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}



	public Integer getManagerId() {
		return managerId;
	}



	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}



	public String getJobTitle() {
		return jobTitle;
	}



	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}



	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", phone=" + phone + ", hireDate=" + hireDate + ", managerId=" + managerId
				+ ", jobTitle=" + jobTitle + "]";
	}
	
	
	
	
}
