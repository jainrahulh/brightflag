package com.brightflag.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.brightflag.domain.Student;

@Repository
public class StudentRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Student> getStudents() {
		return jdbcTemplate.query("SELECT studentID, firstName, lastName FROM student;",
				new BeanPropertyRowMapper<Student>(Student.class));
	}

	public Student searchStudent(int sid) {
		Student student = new Student();
		try{
			student= this.jdbcTemplate.queryForObject("SELECT studentID,firstName,lastName FROM student s WHERE s.studentID=?",
							new BeanPropertyRowMapper<Student>(Student.class), sid);

		} catch(DataAccessException dae){
			System.err.println(dae.getMessage());
		}
		return student;
	}
	
	public boolean addStudent(Student student) {
		int added = this.jdbcTemplate.update("INSERT INTO student(studentID,firstName,lastName) VALUES (?,?,?)",
				student.getFirstName(),student.getLastName());

		return (added == 1);
	}
	
}
