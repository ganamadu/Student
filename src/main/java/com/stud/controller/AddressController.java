package com.stud.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stud.core.Address;
import com.stud.service.AddressService;

@RestController
@RequestMapping("/addr")
public class AddressController {
	private static final Logger LOG = Logger.getLogger(AddressController.class.getName());
	@Autowired
	AddressService addressService;

	@GetMapping("/studentNo/{sno}")
	public List<Address> getAddressByStudentId(@PathVariable Integer sno) {
		return addressService.getAddressByStudentId(sno);
	}

}
