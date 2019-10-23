package com.brightflag.controller;

import java.util.List;

import com.brightflag.service.ExamService;
import com.brightflag.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brightflag.domain.Student;
import com.brightflag.service.StudentService;

@RestController
public class StudentAPIController {

	@Autowired
	StudentService studentService;

	@Autowired
	SubjectService subjectService;

	@Autowired
	ExamService examService;

	@RequestMapping("getStudents")
	public List<Student> getStudents() {
		List<Student> students = studentService.getStudents();
		for(Student student : students) {
			student.setSubjects(subjectService.getSubjectsOfStudent(student));
			student.setExams(examService.listExamOfStudent(student));
			student.setGrades(examService.listGradeOfStudent(student));
		}
		
		return students;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("searchStudent/{studentId}")
	public ResponseEntity<?> searchStudent(@PathVariable String studentId) {
		Student student = studentService.searchStudent(Integer.parseInt(studentId));
		if (student.getStudentID() == null) {
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
		}
		student.setSubjects(subjectService.getSubjectsOfStudent(student));
		student.setExams(examService.listExamOfStudent(student));
		student.setGrades(examService.listGradeOfStudent(student));
		return new ResponseEntity(student, HttpStatus.OK);
	}

	@RequestMapping("assignSubject")
	public boolean assignSubject(@RequestParam String studentId, @RequestParam String subjectId) {
		return subjectService.assignSubject(Integer.parseInt(studentId), Integer.parseInt(subjectId));
	}
}