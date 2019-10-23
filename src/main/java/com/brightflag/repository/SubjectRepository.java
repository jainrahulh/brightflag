package com.brightflag.repository;

import com.brightflag.domain.Student;
import com.brightflag.domain.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubjectRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Subject> subjectList() {
        return this.jdbcTemplate.query("SELECT subjectID,subjectName FROM subject",
                new BeanPropertyRowMapper<Subject>(Subject.class));
    }

    public List<Subject> getSubjectsOfStudent(Student std) {
        List<Subject> subjectList=
                this.jdbcTemplate.query("SELECT Su.subjectID, Su.subjectName\n" +
                        "FROM Student AS S\n" +
                        "INNER JOIN studentSubjects AS SS\n" +
                        "    ON S.studentID = SS.studentID\n" +
                        "INNER JOIN subject AS Su\n" +
                        "    ON SS.subjectID = Su.subjectID WHERE S.studentID = ?",
                        new BeanPropertyRowMapper<Subject>(Subject.class), std.getStudentID());

        return subjectList;
    }

    public boolean createNewSubj(Subject subj) {
        int added= jdbcTemplate.update("INSERT INTO subject(subjectID,subjectName) VALUES (?,?)",
                subj.getSubjectName());

        return (added == 1);
    }

    public boolean assignSubject(int studentId, int subjectId) {
        int added= jdbcTemplate.update("INSERT INTO studentSubjects(studentID,subjectID) VALUES (?,?)",
                studentId, subjectId);

        return (added == 1);
    }
}