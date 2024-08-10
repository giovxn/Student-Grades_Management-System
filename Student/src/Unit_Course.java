/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gio
 */
public class Unit_Course extends Unit{
    private String unitID;
    private int unitLevel;
    private int assignmentOne;
    private int assignmentTwo;
    private int[] labWork = new int[12];
    private int finalExam;
    private double overallMark;
    private String finalGrade;

    //constructor
    public Unit_Course(String unitID, int unitLevel, int assignmentOne, int assignmentTwo, int[] labWork, int finalExam){
        super("C");
        this.unitID = unitID;
        this.unitLevel = unitLevel;
        setAssignmentOne(assignmentOne);
        setAssignmentTwo(assignmentTwo);
        setLabWork(labWork);
        setFinalExam(finalExam);
    }

    //setters
    public void setAssignmentOne(int mark){
        if(mark>0 && mark<=100){
            this.assignmentOne = mark;
        }
        else{
            this.assignmentOne = 0;
        }
    }
    public void setAssignmentTwo(int mark){
        if(mark>0 && mark<=100){
            this.assignmentTwo = mark;
        }
        else{
            this.assignmentTwo = 0;
        }
    }
    public void setLabWork(int[] marks){
        this.labWork = marks;
    }
    public void setFinalExam(int mark){
        if(mark>0 && mark<=100){
            this.finalExam = mark;
        }
        else{
            this.finalExam = 0;
        }
    }

    //getter for getting final grade
    public String getFinalGrade(){
        this.overallMark = calculateOverallMark();
        this.finalGrade = calculateFinalGrade();
        return finalGrade;
    }

    //calculates overall mark by dividing mark by 100 and multiplying by the weight percentage of that task to get the mark
    public double calculateOverallMark(){
        //assignments - 40%   (a1-20%    a2-20%)
        //best 10 labs - 20%
        //final exam - 40%
        double assignmentOneMark = (double)(assignmentOne/100)*20;
        double assignmentTwoMark = (double)(assignmentTwo/100)*20;
        double assignmentMark = assignmentOneMark+assignmentTwoMark; 

        double labWorkAvg = 0;
        insertionSort(labWork);     //sorting to ascending order
        for(int i=2; i<labWork.length; i++){
            labWorkAvg += (double)labWork[i];
        }
        labWorkAvg = (double)labWorkAvg/10;
        double labWorkMark = (labWorkAvg/100.0)*20;
        
        double finalExamMark = (finalExam/100.0)*40; 

        return assignmentMark+labWorkMark+finalExamMark;
    }

    //method for checking what the student got as a final grade based on their overall mark
    public String calculateFinalGrade(){
        double overallMark = calculateOverallMark();
        if(overallMark >= 80){
            return "HD";
        }
        if(overallMark >= 70){
            return "D";
        }
        if(overallMark >= 60){
            return "C";
        }
        if(overallMark >= 50){
            return "P";
        }
        return "N";
    }

    //helper method for sorting an array
    private static void insertionSort(int[] array){
        for (int i=1; i < array.length; i++){
            int temp = array[i];
            int j = i - 1;
            while(j>=0 && array[j]>temp) {
                array[j+1] = array[j];
                j = j-1;
            }
            array[j+1] = temp;
        }
    }
    
}

