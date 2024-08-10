/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gio
 */
public class Research extends Unit{
    private String enrolmentType;
    private int proposal;
    private int finalDissertation;
    private double overallMark;
    private String finalGrade;

    //constructors
    public Research(){
        super("R");
    }

    public Research(int proposal, int finalDissertation){
        setProposalMark(proposal);
        setDissertationMark(finalDissertation);
    }

    //setters
    public void setProposalMark(int mark){
        if(mark>0 && mark<=100){
            this.proposal = mark;
        }
        else{
            this.proposal = 0;
        }
    }
    public void setDissertationMark(int mark){
        if(mark>0 && mark<=100){
            this.finalDissertation = mark;
        }
        else{
            this.finalDissertation = 0;
        }
    }

    //getter for getting final grade
    public String getFinalGrade(){
        this.overallMark = calculateOverallMark();
        this.finalGrade = calculateFinalGrade();
        return finalGrade;
    }

    //takes research marks of proposal and dissertation and calculates the overall mark based on weightage of that task
    public double calculateOverallMark(){
        //proposal (35%)
        //dissertation (65%)
        double proposalMark = (double)(proposal/100.0)*35;
        double dissertationMark = (double)(finalDissertation/100.0)*65;
        return proposalMark+dissertationMark;
    }

    //gets final grade based on overall mark
    public String calculateFinalGrade(){
        overallMark = calculateOverallMark();
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
}

