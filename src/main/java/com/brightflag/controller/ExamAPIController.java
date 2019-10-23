package com.brightflag.controller;

import com.brightflag.domain.Exam;
import com.brightflag.domain.Grade;
import com.brightflag.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExamAPIController {
    @Autowired
    ExamService examService;

    @RequestMapping("createExam")
    public boolean createExam(@RequestBody Exam exam) { 
    	return examService.createExam(exam); 
    }

    @RequestMapping("createGrade")
    public boolean createEGrade(@RequestBody Grade grade) {
        return examService.createGrade(grade);
    }
}