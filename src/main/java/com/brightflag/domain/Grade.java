package com.brightflag.domain;

public class Grade {

	private Integer examID;
	private String examName;
    private int studentID;
    private String grade;
    private int gradeID;
    

	public Integer getExamID() {
		return examID;
	}

	public void setExamID(Integer examID) {
		this.examID = examID;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getGradeID() {
		return gradeID;
	}

	public void setGradeID(int gradeID) {
		this.gradeID = gradeID;
	}

	@Override
	public String toString() {
		return "Grade [examID=" + examID + ", examName=" + examName + ", studentID=" + studentID + ", grade=" + grade
				+ ", gradeID=" + gradeID + "]";
	}

	
}
