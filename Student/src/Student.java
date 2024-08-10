/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gio
 */
public class Student{
    private String firstName;
    private String lastName;
    private long studentID;
    private String DOB;

    //default constructor
    public Student(){
        this.firstName = "No first name";
        this.lastName = "No last name";
        this.studentID = 0;
        this.DOB = "No date";
    }

    //constructor for setting initial values for all
    public Student(String firstName, String lastName, long studentID, String DOB){
        setFirstName(firstName);
        setLastName(lastName);
        setStudentID(studentID);
        setDOB(DOB);
    }

    //setters
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setStudentID(long studentID){
        this.studentID = studentID;
    }
    public void setDOB(String DOB){
        this.DOB = DOB;
    }

    //getters
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public long getStudentID(){
        return studentID;
    }
    public String getDOB(){
        return DOB;
    }

    //method to output grade
    public void reportGrade(){
        System.out.println("There is no grade here");
    }
    public boolean equals(Student[] students){
        for(int i=0; i<students.length; i++){
            for(int j=i+1; j<students.length; j++){
                if(students[i].getStudentID() == (students[j].getStudentID())){
                    return true;
                }
            }
        }
        return false;
    }
    //displays to terminal student information
    public void info(){
        System.out.println("First name: "+firstName);
        System.out.println("Last name: "+lastName);
        System.out.println("Student ID: "+studentID);
        System.out.println("Date of Birth: "+DOB);
    }
}

