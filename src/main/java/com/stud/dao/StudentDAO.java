package com.stud.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stud.core.Student;

@Repository
public interface StudentDAO extends CrudRepository<Student, Serializable> {

	@Query("select s from Student s")
	public List<Student> getStudentList();

	@Query("select s from Student s where s.sno=:sno")
	public Optional<Student> getStudentById(@Param("sno") Integer sno);
	
	@Query("select s from Student s where s.sno=:sno")
	Page<Student> getStudentPageById(@Param("sno") Integer sno, Pageable pageable);
	
	@Query("select s from Student s")
	public Page<List<Student> > getStudentPageList(Pageable pageable);


}
