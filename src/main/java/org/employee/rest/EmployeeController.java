package org.employee.rest;

import java.util.ArrayList;
import java.util.List;

import org.employee.controller.Employee;
import org.employee.dao.ImageDao;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	private List<Employee> Employees = new ArrayList<>();
	
	@Autowired
	private ImageDao theImageDao;
	
	@GetMapping("filter")
	public List<Employee> getEmployee() {
		 Employees = theImageDao.EmpList();
		return Employees;	
	}
	
	@GetMapping("filter/{condition}")
	public List<Employee> getEmployee(@PathVariable String condition ){
		String conditionSql ="'"+condition+"'";
		String departmentQuery="Select * from employee where department="+conditionSql;
		Employees = theImageDao.EmpList(departmentQuery);
		if(!Employees.isEmpty()) {
			return Employees;
		}
		String positionQuery="Select * from employee where position="+conditionSql;
		Employees = theImageDao.EmpList(positionQuery);
		if (!Employees.isEmpty()) {
			return Employees;
		}
		String paginationQuery="Select * from employee Limit "+condition;
		Employees = theImageDao.EmpList(paginationQuery);
		if(!Employees.isEmpty()) {
			return Employees;
		}
		return null;
		
	}
	
	@GetMapping("pagination/{N}")
	public List<Employee> getPagination(@PathVariable String N){
		String pagination = "Select * from employee limit "+N;
		Employees = theImageDao.EmpList(pagination);
		if(!Employees.isEmpty()) {
			return Employees;
		}
		return null;
	}
	
	@PostMapping("filter")
	public List<Employee> filterEmployee(@RequestBody EmployeeRequestBody theEmployeeRequestBody){
		if(!isNullEmpty(theEmployeeRequestBody.getDepartment())) {
			String department = theEmployeeRequestBody.getDepartment();
			String departmentQuery="Select * from employee where department="+"'"+department+"'";
			Employees = theImageDao.EmpList(departmentQuery);
			return Employees;
		}
		else if(!isNullEmpty(theEmployeeRequestBody.getPosition())) {
			String position = theEmployeeRequestBody.getPosition();
			String positionQuery="Select * from employee where position="+"'"+position+"'";
			Employees = theImageDao.EmpList(positionQuery);
			return Employees;	
		}else {
			String paginationLimit = "Select * from employee limit "+theEmployeeRequestBody.getPaginationLimit();
			Employees = theImageDao.EmpList(paginationLimit);// check condition
			return Employees;
		}
	}
	
	public boolean isNullEmpty(String s) {
		if (s == null) {
		      return true;
		    }
		else if(s.isEmpty()){
		      return true;
		    }

		else {
		      return false;
		    }
	}
	
}
