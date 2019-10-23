package com.brightflag.service;

import com.brightflag.domain.Exam;
import com.brightflag.domain.Grade;
import com.brightflag.domain.Student;
import com.brightflag.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    ExamRepository examRepository;

    public boolean createExam(Exam exam) { 
    	return examRepository.createNewExam(exam);
    }

    public boolean createGrade(Grade grade) { 
    	return examRepository.createGrade(grade); 
    }
    
    public List<Exam> listExamOfStudent(Student std) {
        return examRepository.listExamOfStudent(std);
    }

    public List<Grade> listGradeOfStudent(Student std) {
        return examRepository.listGradeOfStudent(std);
    }
}