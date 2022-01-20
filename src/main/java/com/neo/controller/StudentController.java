package com.neo.controller;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neo.entity.Student;

import com.neo.service.StudentServiceImpl;

@RestController
@RequestMapping("/api/student/")
public class StudentController {
	@Autowired
	private StudentServiceImpl stuService;
	
	@PostMapping("/")
	public ResponseEntity<Student> registerStudent(@RequestBody Student student){
		Student registerStudent = stuService.saveStudent(student);
		return new ResponseEntity<Student>(registerStudent, HttpStatus.CREATED);
		
	}
	@GetMapping("/")
	public ResponseEntity<List<Student>> getAllStudent() {
		List<Student> allUsers = stuService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(allUsers);
}
	@GetMapping("/{sid}")
	public ResponseEntity<Optional<Student>> getStudent(@PathVariable Integer sid){
		Optional<Student> student = stuService.getStudent(sid);
		return ResponseEntity.status(HttpStatus.OK).body(student);
	}
}