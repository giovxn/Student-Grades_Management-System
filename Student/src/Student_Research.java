/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gio
 */
public class Student_Research extends Student{
    private String enrolmentType;
    private Research research;

    //constructors
    //default
    public Student_Research(){
        super();
        this.enrolmentType = "R";
    }

    public Student_Research(String firstName, String lastName, long studentID, String DOB, Research research){
        super(firstName, lastName, studentID, DOB);
        this.enrolmentType = "R";
        this.research = research;
    }

    //setter
    public void setResearch(Research research){
        this.research = research;
    }

    //getter
    public Research getResearch(){
        return research;
    }

    //reports grade to terminal
    public void reportGrade(){
        double overallMark = research.calculateOverallMark();
        String finalGrade = research.calculateFinalGrade();
        
        System.out.println("Enrolment Type: "+enrolmentType);
        System.out.println("Student Name: "+getFirstName()+" "+getLastName());
        System.out.println("Student ID: "+getStudentID());
        System.out.println("Overall Mark: "+(int)overallMark);
        System.out.println("Final Grade: "+finalGrade);
    }
}

