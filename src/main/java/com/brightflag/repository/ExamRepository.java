package com.brightflag.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.brightflag.domain.Exam;
import com.brightflag.domain.Grade;
import com.brightflag.domain.Student;

@Repository
public class ExamRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Exam> getAllExams() {
		return jdbcTemplate.query("SELECT examID,examName,studentID,subjectID FROM exam",
				new BeanPropertyRowMapper<Exam>(Exam.class));
	}

	public List<Grade> listGradeOfStudent(Student std) {
		List<Grade> gradeList=
				this.jdbcTemplate.query("SELECT g.gradeID, g.examID, e.examName, g.studentID, g.grade\n" +
			            "FROM exam AS e\n" +
			            "INNER JOIN grade AS g\n" +
			            "    ON e.examID = g.examID  WHERE e.studentID = ?",
						new BeanPropertyRowMapper<Grade>(Grade.class), std.getStudentID());

		return gradeList;
	}

	public List<Exam> listExamOfStudent(Student std) {
		List<Exam> examList=
				this.jdbcTemplate.query("SELECT examID, examName, studentID, subjectID FROM exam WHERE studentID=?",
						new BeanPropertyRowMapper<Exam>(Exam.class), std.getStudentID());

		return examList;
	}

	public Exam findExamByID(int id) {
		Exam exam = new Exam();
		try{
			exam= this.jdbcTemplate.queryForObject("SELECT examID, examName, studentID, subjectID FROM exam WHERE examID=?",
					new BeanPropertyRowMapper<Exam>(Exam.class), id);

		}catch(DataAccessException dae){
			System.err.println(dae.getMessage());
		}
		return exam;
	}

	public boolean createNewExam(Exam exam) {
		int added= jdbcTemplate.update("INSERT INTO exam(examName, studentID, subjectID) VALUES (?,?,?)",
				exam.getExamName(), exam.getStudentID(), exam.getSubjectID());

		return (added == 1);
	}

	public boolean createGrade(Grade grade) {
		int added= jdbcTemplate.update("INSERT INTO grade(examID, studentID, grade) VALUES (?,?,?)",
				grade.getExamID(), grade.getStudentID(), grade.getGrade());

		return (added == 1);
	}
}
