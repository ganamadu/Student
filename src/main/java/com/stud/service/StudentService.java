package com.stud.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stud.application.StudentApplication;
import com.stud.common.MultiThreadEx;
import com.stud.core.Address;
import com.stud.core.Student;
import com.stud.dao.AddressDAO;
import com.stud.dao.StudentDAO;

@Service
public class StudentService {
	private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);

	MultiThreadEx threadPool = MultiThreadEx.getInstance();

	Queue<Future<Object>> futures = new ConcurrentLinkedDeque<Future<Object>>();

	@Autowired
	StudentDAO studentDAO;

	@Autowired
	AddressDAO addressDAO;

	@Transactional
	public void save(Student student) {
		LOG.info("Student Save method...");
		Student stu = saveStudent(student);
		student.getAddressList().forEach(addr -> {
			addr.setStudent(stu);
			addressDAO.save(addr);
		});
	}

	public Student saveStudent(Student student) {
		LOG.info("Student Save method...");
		return studentDAO.save(student);
	}

	public List<Student> getStudentList() {
		LOG.info("Student Get Studetn List method...");
		return studentDAO.getStudentList();
	}
	
	public Optional<Student> getStudentByid(Integer sno) {
		LOG.info("Student Get Student By No method...");
		return studentDAO.getStudentById(sno).map(student->{
			List<Address> addreList = addressDAO.getAddressByStudentId(sno);
			student.setAddressList(addreList);
			return student;
		});
	}
	
	public Page<Student> getStudentByid(Integer sno, Pageable pageable) {
		LOG.info("Student Page Get Student By No method...");
		return studentDAO.getStudentPageById(sno, pageable);
	}
	
	public Page<List<Student>> getStudentPageList(Pageable pageable) {
		LOG.info("Student Page Get Student List method...");
		return studentDAO.getStudentPageList(pageable);
	}
	

	public void saveAll(List<Student> studentList) throws Exception {
		LOG.info("Student Save All method...");
		studentList.forEach(stud -> {
			List<Address> addressList = new ArrayList<>();
			Callable<Object> studentToken = new Callable<Object>() {
				@Override
				public Object call() throws Exception {
					Student std = studentDAO.save(stud);
					stud.getAddressList().forEach(addr -> {
						addr.setStudent(std);
						addressDAO.save(addr);
					});
					return std;
				}
			};
			futures.add(threadPool.getFutureReference(studentToken));
		});
		disp();
	}
	
	public void disp() throws InterruptedException, ExecutionException {
		
		Iterator<Future<Object>> itr = futures.iterator();
		while(itr.hasNext()) {
			Future<Object> obj = itr.next();
			Student std = (Student) obj.get();
			System.out.println(std.getSname());
		}
	}

}
