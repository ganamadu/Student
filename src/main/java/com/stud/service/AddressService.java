package com.stud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stud.application.StudentApplication;
import com.stud.core.Address;
import com.stud.core.Student;
import com.stud.dao.AddressDAO;

@Service
public class AddressService {
	private static final Logger LOG = LoggerFactory.getLogger(StudentApplication.class);
	
	@Autowired
	AddressDAO addressDAO;

	public List<Address> getAddressByStudentId(Integer sno) {
		return addressDAO.getAddressByStudentId(sno);
	}

}
