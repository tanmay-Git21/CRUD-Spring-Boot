package com.tanmay.crudApp.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tanmay.crudApp.Models.Student;




@Repository
public interface DataOperator extends JpaRepository<Student, Integer> {

	
	
	
	
	
	
}
