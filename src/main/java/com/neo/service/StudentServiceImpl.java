package com.neo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.entity.Student;
import com.neo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentServiceI {

	@Autowired
	private StudentRepository stuRepo;

	@Override
	public Student saveStudent(Student student) {

		Student saveStudent = stuRepo.save(student);
		return saveStudent;

	}

	@Override
	public List<Student> getAll() {
		List<Student> list = stuRepo.findAll();
		return list;
	}

	@Override
	public Optional<Student> getStudent(Integer sid) {
		Optional<Student> findById = stuRepo.findById(sid);
		return findById;
	}

}
