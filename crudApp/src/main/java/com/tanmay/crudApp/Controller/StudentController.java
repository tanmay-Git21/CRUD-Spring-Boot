package com.tanmay.crudApp.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tanmay.crudApp.Models.Student;
import com.tanmay.crudApp.Repo.DataOperator;

@RestController
public class StudentController {

	@Autowired
	private DataOperator dataOperator;

	@GetMapping("/getstudents")
	public ResponseEntity<List<Student>> getAllStudents() {

		try {
			List<Student> studentList = new ArrayList<>();

			dataOperator.findAll().forEach(studentList::add);

			if (studentList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(studentList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getStudentById/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable int id) {
		try {
			Optional<Student> studentData = dataOperator.findById(id);

			if (studentData.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(studentData.get(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/addStudent")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {

		try {
			Student studentObj = dataOperator.save(student);
			return new ResponseEntity<>(studentObj, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/updateStudentById/{id}")
	public ResponseEntity<Student> updateStudentById(@PathVariable int id, @RequestBody Student newStudentData) {

		try {
			Optional<Student> oldStudentData = dataOperator.findById(id);

			if (oldStudentData.isPresent()) {
				Student toBeUpdatedData = oldStudentData.get();
				toBeUpdatedData.setName(newStudentData.getName());
				toBeUpdatedData.setRollno(newStudentData.getRollno());
				toBeUpdatedData.setAge(newStudentData.getAge());

				Student updatedStudent = dataOperator.save(toBeUpdatedData);

				return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteStudentById/{id}")
	public ResponseEntity<Student> deleteStudentById(@PathVariable int id) {

		try {
			Optional<Student> studentToBeDel = dataOperator.findById(id);

			if (studentToBeDel.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			dataOperator.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
