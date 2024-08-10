/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gio
 */
public class Unit{
    private String enrolmentType;

    //constructors
    public Unit(){
        super();
    }

    public Unit(String enrolmentType){
        setEnrolmentType(enrolmentType);
    }

    //setter
    public void setEnrolmentType(String enrolmentType){
        this.enrolmentType = enrolmentType;
    }

    //getter
    public String getEnrolmentType(){
        return enrolmentType;
    }

    public void finalGrade(){
        System.out.println("NA");
    }
}

