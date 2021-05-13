package com.stud.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.rmi.StubNotFoundException;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stud.client.StudentRestClient;
import com.stud.core.Department;
import com.stud.core.Employee;
import com.stud.core.Student;
import com.stud.service.StudentService;

@RestController
@RequestMapping("/stud")
public class StudentController {
	private static final Logger LOG = Logger.getLogger(StudentController.class.getName());
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentRestClient studentRestClient;

	@PostMapping("/save")
	public void save(@RequestBody Student student) {
		LOG.info("Controller Student Save method...");
		studentService.save(student);
	}
	
	

	@PostMapping("/saveAll")
	public void saveAll(@RequestBody List<Student> students) throws Exception {
		LOG.info("Controller Student SaveAll method...");
		studentService.saveAll(students);
	}

	@GetMapping("/list")
	public List<Student> getStudentList() {
		LOG.info("Controller Get Student List method...");
		List<Student> stdList = studentService.getStudentList();
		LOG.log(Level.INFO, stdList);
		return stdList;
	}
	
	
	@GetMapping("/sno/{sno}")
	public Student getStudentById(@PathVariable Integer sno) throws Exception {
		System.out.println("Call the getStudentById method...");
		Optional<Student> student = studentService.getStudentByid(sno);
		if(student.isEmpty()) 
			throw new StubNotFoundException("Id: "+sno);
		return student.get();
	}
	
	@GetMapping("/sno/{sno}/page")
	Page<Student> getStudentPage(@PathVariable Integer sno, Pageable pageable) {
		LOG.info("Contoller GET Student Page By No method...");
		return studentService.getStudentByid(sno, pageable);
	}
	
	@GetMapping("/list/page")
	public Page<List<Student>> getStudentPageList(Pageable pageable) {
		LOG.info("Controller Get Student Page Get Student List method...");
		Page<List<Student>> studPageList =  studentService.getStudentPageList(pageable);
		LOG.log(Level.INFO, studPageList);
		return studPageList;
	}
	
	@GetMapping("/emplist")
	public List<Employee> getEmpList() {
		System.out.println("Student Controller calling from Employee list");
		List<Employee> empList =  studentRestClient.getListApi("http://localhost:8080/emp/list", HttpMethod.GET);
		return empList;
	}
	
	@GetMapping("/deptlist")
	public List<Department> getDeptList() {
		System.out.println("Student Controller calling from Department list");
		List<Department> deptList =  studentRestClient.getListApi("http://localhost:8085/dept/list", HttpMethod.GET);
		return deptList;
	}
	
	@GetMapping("/empno/{empno}") 
	public Employee getEmpByEmpno(@PathVariable Integer empno) {
		System.out.println("Student Controll calling from Employ by id url");
		Employee emp = studentRestClient.getObjectApi("http://localhost:8080/emp/empno/"+empno, HttpMethod.GET, Employee.class);
		return emp;
	}
	
	@GetMapping("/deptno/{deptno}") 
	public Department getDeptByDeptno(@PathVariable Integer deptno) {
		System.out.println("Student Controll calling from Employ by id url");
		Department dept = studentRestClient.getObjectApi("http://localhost:8085/dept/deptno/"+deptno, HttpMethod.GET, Department.class);
		return dept;
	}
	
	
	@RequestMapping(value = "/exception")
	public String exception() {
		String response = "";
		try {
			throw new Exception("Exception has occured....");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String stackTrace = sw.toString();
			LOG.error("Exception - " + stackTrace);
			response = stackTrace;
		}

		return response;
	}
	
	
	
	

}
