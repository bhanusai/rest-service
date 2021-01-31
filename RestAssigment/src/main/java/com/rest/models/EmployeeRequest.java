package com.rest.models;

public class EmployeeRequest {

	private String firstName;

	private String lastName;

	private String emailId;

	private Integer age;

	private String gender;

	private String address;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public Integer getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "EmployeeRequest [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", age="
				+ age + ", gender=" + gender + ", address=" + address + "]";
	}

}
