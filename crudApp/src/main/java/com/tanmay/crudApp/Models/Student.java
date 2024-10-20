package com.tanmay.crudApp.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name="Students")
public class Student {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int Id;
	
	@Column
	private String Name;
	
	@Column
	private int Rollno;
	
	@Column
	private int Age;
	
	
	
	
	public Student() {
		super();
	}
	public Student(int id, String name, int rollno, int age) {
		super();
		Id = id;
		Name = name;
		Rollno = rollno;
		Age = age;
	}
	
	
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getRollno() {
		return Rollno;
	}
	public void setRollno(int rollno) {
		Rollno = rollno;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	
	@Override
	public String toString() {
		return "Student [Id=" + Id + ", Name=" + Name + ", Rollno=" + Rollno + ", Age=" + Age + "]";
	}
	
	

}
