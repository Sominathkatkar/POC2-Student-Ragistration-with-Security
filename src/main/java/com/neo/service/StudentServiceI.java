package com.neo.service;

import java.util.List;
import java.util.Optional;

import com.neo.entity.Student;

public interface StudentServiceI {
	
	public Student saveStudent(Student student);
	public List<Student> getAll();
	
	public Optional<Student> getStudent(Integer sid);

}
