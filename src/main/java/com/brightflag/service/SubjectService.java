package com.brightflag.service;

import com.brightflag.domain.Student;
import com.brightflag.domain.Subject;
import com.brightflag.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    public List<Subject> getSubjectsOfStudent(Student student) {
        return subjectRepository.getSubjectsOfStudent(student);
    }

    public List<Subject> subjectList() {
        return subjectRepository.subjectList();
    }

    public boolean assignSubject(int sid, int subjectID) { 
    	return subjectRepository.assignSubject(sid, subjectID); 
    }
}