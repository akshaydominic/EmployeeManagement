package org.employee.rest;

public class EmployeeRequestBody {
	
	private String department;
	private String position;
	private String paginationLimit;
	
	
	public EmployeeRequestBody() {
		
	}
	public EmployeeRequestBody(String department, String position, String paginationLimit) {
		this.department = department;
		this.position = position;
		this.paginationLimit = paginationLimit;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPaginationLimit() {
		return paginationLimit;
	}
	public void setPaginationLimit(String paginationLimit) {
		this.paginationLimit = paginationLimit;
	}
	@Override
	public String toString() {
		return "EmployeeRequestBody [department=" + department + ", position=" + position + ", paginationLimit="
				+ paginationLimit + "]";
	}
	
	
	

}
