package com.stud.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.stud.core.Address;
import com.stud.core.Student;
import com.stud.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
@ComponentScan(basePackages = "com.stud")
class StudentControllerTest {
	
	@MockBean
	StudentService studentService;
	
	

	@Test
	void saveStudent() {
		Student stud = new Student();
		Address addr = new Address();
		addr.setLine1("line1");
		addr.setLine2("line2");
		addr.setCity("city");
		addr.setZip(101010);
		List<Address> addrList = new ArrayList<>();
		addrList.add(addr);
		stud.setSname("Gana");
		stud.setFname("Gana");
		stud.setAddressList(addrList);
		Mockito.when(studentService.saveStudent(stud)).thenReturn(stud);
	}

}
