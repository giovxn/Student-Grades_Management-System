/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gio
 */
public class Student_Course extends Student{
    private String enrolmentType;
    private Unit_Course unitCourse;

    //constructors
    //default
    public Student_Course(){
        super();
        this.enrolmentType = "C";
    }

    public Student_Course(String firstName, String lastName, long studentID, String DOB, Unit_Course unitCourse){
        super(firstName, lastName, studentID, DOB);
        this.enrolmentType = "C";
        this.unitCourse = unitCourse;
    }

    //setter
    public void setUnitCourse(Unit_Course unitCourse){
        this.unitCourse = unitCourse;
    }

    //getter
    public Unit_Course getUnitCourse(){
        return unitCourse;
    }

    public double getOverallMark(){
        double overallMark = unitCourse.calculateOverallMark();
        return overallMark;
    }

    //reports grade to terminal
    public void reportGrade(){
        double overallMark = unitCourse.calculateOverallMark();
        String finalGrade = unitCourse.calculateFinalGrade();

        System.out.println("Enrolment Type: "+enrolmentType);
        System.out.println("Student Name: "+getFirstName()+" "+getLastName());
        System.out.println("Student ID: "+getStudentID());
        System.out.println("Overall Mark: "+(int)overallMark); //coverting to int
        System.out.println("Final Grade: "+finalGrade);
    }
}

