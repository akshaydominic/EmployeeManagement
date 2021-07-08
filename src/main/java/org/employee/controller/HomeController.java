package org.employee.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.employee.dao.ImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	ImageDao imageDao;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	
	@RequestMapping("home")
	public String home(Model theModel) {
		theModel.addAttribute("employee",new Employee());
		return "home";
	}
	@RequestMapping(value="signUp")
	public String signUp(@ModelAttribute("employee") Employee theEmployee,
			@RequestParam("picture") MultipartFile picture) {
			if(theEmployee.getId()!=0) {
				try {
					imageDao.updateEmployeeById(theEmployee, picture);
				}catch(Exception e) {
					System.out.println(e);
				}
			}else {
				try {
					imageDao.inserRecords(theEmployee,picture);

				} catch (Exception e) {
					System.out.println(e);
					}	
			}
		return "redirect:list";
	}
	@RequestMapping("list")
	public String displayList(Model theModel) {
		List<Employee> employees = imageDao.EmpList();
		theModel.addAttribute("employees",employees);
		return "list";
	}
	@RequestMapping(value = "/getEmployeePhoto/{id}")
	public void getStudentPhoto(HttpServletResponse response, @PathVariable("id") Integer id) throws Exception {
		response.setContentType("image/jpeg");
		Blob ph = imageDao.getPhotoById(id);
		byte[] bytes = ph.getBytes(1, (int) ph.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}
	@RequestMapping(value="/delete")
	public String deleteEmployeeById(@RequestParam("employeeId") int id) {
		try {
			imageDao.deleteEmployeeById(id);
		}catch(Exception e) {
			System.out.println(e);
		}
		return "redirect:list";
	}
	
	@RequestMapping(value="/showFormForUpdate")
	public String showUpdateForm(@RequestParam("employeeId") int id,Model theModel) {
		Employee theEmployee = new Employee();
		theEmployee.setId(id);
		theModel.addAttribute("employee",theEmployee);
	
		return "home";
		
		
		
	}
	
}
