package org.employee.dao;

import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.employee.controller.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.MultipartFile;

public class ImageDao {
	private JdbcTemplate jdbcTemplate;

	public ImageDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}



	public int inserRecords(Employee theEmployee,MultipartFile picture) throws IOException {

		byte[] photoBytes = picture.getBytes();

		String sql = "INSERT INTO employee(first_name,last_name,salary,department,position,email_address,contact_number,picture) VALUES (?,?,?,?,?,?,?,?)";

		return jdbcTemplate.update(sql, new Object[] {theEmployee.getFirstName(),theEmployee.getLastName(),
				theEmployee.getSalary(),theEmployee.getDepartment(),
				theEmployee.getPosition(),theEmployee.getEmail(),
				theEmployee.getContact(),photoBytes});
	}
	
	public List<Employee> EmpList() {

		List<Employee> list = jdbcTemplate.query("SELECT * FROM employee", new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee emp = new Employee();

				emp.setId(rs.getInt("id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setSalary(rs.getInt("salary"));
				emp.setDepartment(rs.getString("department"));
				emp.setPosition(rs.getString("position"));
				emp.setEmail(rs.getString("email_address"));
				emp.setContact(rs.getLong("contact_number"));
				return emp;
			}
		});

		return list;
	}
	public Blob getPhotoById(Integer id) {

		String query = "select picture from employee where id=?";

		Blob photo = jdbcTemplate.queryForObject(query, new Object[] { id }, Blob.class);

		return photo;
	}
	
	public void deleteEmployeeById(int id) {
		String deleteSql = "DELETE FROM employee WHERE id = ?";
		
		Object[] params = { id };	   
	        int[] types = {Types.BIGINT};
	 
	     jdbcTemplate.update(deleteSql, params, types);
	}
	public void updateEmployeeById(Employee theEmployee,MultipartFile picture) throws IOException{
		byte[] photoBytes = picture.getBytes();
		String updateSql= "update employee set first_name=?,last_name=?,salary=?,department=?,position=?"
				+ ",email_address=?,contact_number=?,picture=? where id = ?";
		jdbcTemplate.update(updateSql,theEmployee.getFirstName(),theEmployee.getLastName(),theEmployee.getSalary(),
				theEmployee.getDepartment(),theEmployee.getPosition(),theEmployee.getEmail(),theEmployee.getContact(),photoBytes,
				theEmployee.getId());
		
	}
	public List<Employee> EmpList(String s) {

		List<Employee> list = jdbcTemplate.query(s, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee emp = new Employee();

				emp.setId(rs.getInt("id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setSalary(rs.getInt("salary"));
				emp.setDepartment(rs.getString("department"));
				emp.setPosition(rs.getString("position"));
				emp.setEmail(rs.getString("email_address"));
				emp.setContact(rs.getLong("contact_number"));
				return emp;
			}
		});

		return list;
	}


}
