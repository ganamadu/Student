package com.stud.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stud.core.Address;

@Repository
public interface AddressDAO extends CrudRepository<Address, Serializable> {

	@Query("select a from Address a where a.addressid=:addressId")
	public Address getAddressById(@Param("addressId") Integer addressId);

	@Query("select a from Address a where a.student.sno=:sno")
	public List<Address> getAddressByStudentId(@Param("sno") Integer sno);

}
