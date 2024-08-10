/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gio
 */
/*
ICT167 Principles of Computer Science
Assignment 2

Title: Student Grades Management System
Author: Giovanni Icasiano Jr
Date: 27th of July, 2024
File Names:
    - Student.java
    - Student_Course.java
    - Student_Research.java
    - Unit.java
    - Unit_Course.java
    - TestStudent.java
    - student.csv
    - studentMarks.csv
Purpose: The Student Grades Management System is designed to manage student information, including names, student IDs, and enrollment types. The system reads data from CSV files, processes the information, and provides functionalities like sorting and reporting grades. Assumptions include that input data in the CSV files is correctly formatted and that the program will output sorted student lists and grade reports based on this input.
Assumption(s):
Condition(s):
 */

import java.io.*;
import java.util.*;

public class TestStudent{
    static Scanner kb = new Scanner(System.in); 
    public static void main(String[] args){
        ArrayList<Student> studentArrayList = new ArrayList<>();
   
        readStudentData(studentArrayList);  //read and store data to array list

        //display menu and get menu choice
        int menuChoice;
        boolean sorted = false;         //used as a error handling method to prevent writing to csv unsorted, array list must be sorted first (true)
        boolean marksAdded = false;     //used as a error handling method to prevent errors from occuring, some cases in the switch case require marks to be stored in array list to perform the method
        do{
            displayMenu();
            menuChoice = kb.nextInt();
            kb.nextLine();
            switch(menuChoice){                                    
                case 1: 
                    System.out.println("\n========================================\nExiting...\n========================================\n");
                    return;
                case 2:
                    if(marksAdded == true){ //prevents user from duplicating marks 
                        System.out.println("ERROR: Marks have already been added, please press 1 to exit and try again.");
                        break;
                    }
                    readStudentMarks(studentArrayList);
                    marksAdded = true;
                    break;
                case 3:
                    if(marksAdded == false){
                        System.out.println("\nERROR: Marks were not added yet, please select 2 to add marks.\n");
                        break;
                    }
                    removeStudent(studentArrayList);
                    break;
                case 4:
                    outputDetails(studentArrayList);
                    break;
                case 5:
                    if(marksAdded == false){
                        System.out.println("\nERROR: Marks were not added yet, please select 2 to add marks.\n");
                        break;
                    }
                    double average = avgNum(studentArrayList);
                    aboveOrBelow(studentArrayList, average);
                    break;
                case 6:
                    if(marksAdded == false){
                        System.out.println("\nERROR: Marks were not added yet, please select 2 to add marks.\n");
                        break;
                    }
                    reportGradeInfo(studentArrayList);
                    break;
                case 7:
                    sortArrayList(studentArrayList);
                    outputDetails(studentArrayList);
                    sorted = true;
                    break;
                case 8:
                    writeToCSV(studentArrayList, sorted);
                    break;    
                default:                                            
                    System.out.println("ERROR: Invalid input, try again.");
            }
        }while(menuChoice != 1);  

        //display information from ArrayList
        System.out.println("Students in ArrayList:");
        for (Student student : studentArrayList){
            student.info();
            System.out.println();
        }
    }
    /* Methods */

    //method for displaying menu to terminal
    public static void displayMenu(){
        System.out.println("========================================\nPick an option:\n");
        System.out.println("1. Exit");
        System.out.println("2. Read student marks data and store");
        System.out.println("3. Remove student details");
        System.out.println("4. Display all student details");
        System.out.println("5. Display above and below overall mark average");
        System.out.println("6. Report grade for a student");
        System.out.println("7. Sort and output student details");
        System.out.println("8. Output sorted student details to CSV");
        System.out.println("========================================");
        System.out.printf("\nChoice: ");
    }

    //method for writing array list data to user's choice of csv file name, method ensures arraylist is sorted appropriately
    public static void writeToCSV(ArrayList<Student> studentArrayList, boolean sorted){
        if(sorted == false){
            System.out.println("ERROR: Data is not sorted.");
            return;
        }
        System.out.printf("Enter filename for output: ");
        String outputFileName = kb.nextLine();
        PrintWriter outputStream = null;
        try{
            outputStream = new PrintWriter(outputFileName);
        }
        catch (FileNotFoundException e){
            System.out.println("Error opening"+" the file " +outputFileName);
            System.exit(0);
        }
        for(Student student : studentArrayList){
            outputStream.printf("%s %s, %d, %s\n", student.getFirstName(), student.getLastName(), student.getStudentID(), student.getDOB());
        }
        outputStream.close();
    }

    //method for sorting students into ascending order of studentID
    public static void sortArrayList(ArrayList<Student> studentArrayList){
        int[] studentIDs = new int[studentArrayList.size()];
        for(int i=0; i<studentArrayList.size(); i++){
            studentIDs[i] = (int) studentArrayList.get(i).getStudentID();
        }

        insertionSort(studentIDs);

        ArrayList<Student> tempList = new ArrayList<>();
        for(int id : studentIDs){
            for(Student student : studentArrayList){
                if(student.getStudentID() == id){
                    tempList.add(student);
                    break;
                }
            }
        }
        studentArrayList.clear();
        studentArrayList.addAll(tempList);
        System.out.println("\nList sorted successfully.");
    }

    //helper method for sorting an array to ascending order
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

    //method for reporting the grade of student, method asks for student ID and it searches for the student in the arraylist
    public static void reportGradeInfo(ArrayList<Student> studentArrayList){
        System.out.printf("Enter student number: ");
        long studentID = kb.nextInt();
        for(Student student : studentArrayList){
            if(student.getStudentID() == studentID){
                System.out.println("");
                student.reportGrade();
                System.out.println("");
                return;
            }
        }
        System.out.println("ERROR: Student not found");
    }

    //method for checking how many students are below and above average 
    public static void aboveOrBelow(ArrayList<Student> studentArrayList, double average){
        int above = 0;
        int below = 0;

        for(Student student : studentArrayList){
            if(student instanceof Student_Course){      //ensures student is an instance of Student_Course
                double overallMark = ((Student_Course)student).getOverallMark();
                if(overallMark >= average){
                    above++;
                }
                if(overallMark < average){
                    below++;
                }
            }
        }
        System.out.printf("\nNumber of students with marks above or equal to the average: %d", above);
        System.out.printf("\nNumber of students with marks below the average: %d\n", below);
    }

    //method for getting average overall mark, returns a double
    public static double avgNum(ArrayList<Student> studentArrayList){
        double total = 0;
        int count = 0;
        for(Student student : studentArrayList){
            if(student instanceof Student_Course){
                total += ((Student_Course)student).getOverallMark();
                count++;
            }
        }
        double average = total/count;
        System.out.printf("\nAverage overall mark: %.2f\n",average);

        return average;
    }

    //method for displaying all of students data
    public static void outputDetails(ArrayList<Student> studentArrayList){
        for(Student student : studentArrayList){
            System.out.printf("\nStudent name: %s %s\n", student.getFirstName(), student.getLastName());
            System.out.printf("Student number: %d\n", student.getStudentID());
            System.out.printf("Date of Birth: %s\n", student.getDOB());
        }
    }

    //method for removing student data, user searches for student using student ID and program asks for confirmation to delete student's data in the arraylist
    public static void removeStudent(ArrayList<Student> studentArrayList){
        System.out.printf("Enter student number: ");
        long studentID = kb.nextLong();
        kb.nextLine();
        int index = 0;
        for(Student student : studentArrayList){
            if(student.getStudentID() == studentID){
                System.out.printf("\nAre you sure you want to remove this data? (Y/N): ");
                String confirm = kb.nextLine();
                if(confirm.equalsIgnoreCase("Y")){
                    studentArrayList.remove(index);
                    System.out.println("\nStudent removed successfully.\n");
                    return;
                }
                else{
                    return;
                }
            }
            index++;
        }
        System.out.println("ERROR: Student not found");
    }

    //method for reading the student marks, user inputs file name and method reads proper data and store in array list
    public static void readStudentMarks(ArrayList<Student> studentArrayList){
        System.out.printf("Enter file name: ");
        String fileName = kb.nextLine();
        Scanner inputStream = null;
        try{
            int index = 0;
            inputStream = new Scanner(new File(fileName));
            //gather student marks information
            while(inputStream.hasNextLine()){
                String inputLine = inputStream.nextLine();
                StringTokenizer token = new StringTokenizer(inputLine, ",");
                
                String lastName = token.nextToken();
                String enrolmentType = token.nextToken();
                
                Student student = studentArrayList.get(index);
                //checks enrolment type and confirms last name corresponds with the students marks information
                if(enrolmentType.equals("C") && lastName.equals(studentArrayList.get(index).getLastName())){
                    Unit_Course unitCourse = readDataCourse(token);
                    ((Student_Course)student).setUnitCourse(unitCourse);
                }
                else if(enrolmentType.equals("R")&& lastName.equals(studentArrayList.get(index).getLastName())){
                    Research research = readDataResearch(token);
                    ((Student_Research)student).setResearch(research);
                }
                else{
                    System.out.println("ERROR: Student marks not found");
                }
                index++;
            }
        }
        catch(FileNotFoundException e){
            System.out.printf("ERROR: Unable to open %s.", fileName);
            System.exit(0);
        }
        
        System.out.println("\nSuccessfully read and stored student marks.\n");
        inputStream.close();
    }

    //method for shortening method readStudentMarks. reads and sets necessary data to course enrolment type
    public static Unit_Course readDataCourse(StringTokenizer token){
        String unitID = token.nextToken();
        int unitLevel = Integer.parseInt(token.nextToken());
        int assignmentOne = Integer.parseInt(token.nextToken());
        int assignmentTwo = Integer.parseInt(token.nextToken());

        int[] labMarks = new int[12];
        for (int i=0; i<labMarks.length; i++) {
            labMarks[i] = Integer.parseInt(token.nextToken());
        }

        int finalExam = Integer.parseInt(token.nextToken());

        return new Unit_Course(unitID, unitLevel, assignmentOne, assignmentTwo, labMarks, finalExam);
    }

    //method for shortening method readStudentMarks. read and set necessary data to research enrolment type
    public static Research readDataResearch(StringTokenizer token){
        int proposalMark = Integer.parseInt(token.nextToken());
        int dissertationMark = Integer.parseInt(token.nextToken());
    
        return new Research(proposalMark, dissertationMark);
    }

    //method for reading file student.csv and store in array list
    public static void readStudentData(ArrayList<Student> studentArrayList){
        Student_Course studentCourse = null;        
        Student_Research studentResearch = null;

        String fileName = "student.csv";    
        Scanner inputStream = null;
        try{
            inputStream = new Scanner(new File(fileName));
            //gather student information
            while(inputStream.hasNextLine()){
                
                String inputLine = inputStream.nextLine();
                StringTokenizer token = new StringTokenizer(inputLine, ",");    //StringTokenizer is used for separating string in the csv file. Comma is used as a detection on how to split and differentiate data 
                
                String firstName = token.nextToken();
                String lastName = token.nextToken();
                int studentID = Integer.parseInt(token.nextToken());
                String DOB = token.nextToken();
                
                String enrolmentType = token.nextToken();
                
                //check if enrolment type is C, then store student information to arraylist<student>
                if(enrolmentType.equals("C")){
                    addStudentInfo("C", studentCourse, studentArrayList, firstName, lastName, studentID, DOB);
                }
                //check if enrolment type is R, then store student information to arraylist<student>
                else if(enrolmentType.equals("R")){
                    addStudentInfo("R", studentResearch, studentArrayList, firstName, lastName, studentID, DOB);
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.printf("ERROR: Unable to open %s.", fileName);
            System.exit(0);
        }
        
        inputStream.close();
    }

    //helper method for creating student course and research section and store necessary data in arraylist
    private static void addStudentInfo(String enrolmentType, Student subClass, ArrayList<Student> studentArrayList, String firstName, String lastName, int studentID, String DOB){
        if(enrolmentType.equals("C")){
            subClass = new Student_Course();
        }
        if(enrolmentType.equals("R")){
            subClass = new Student_Research();
        }
        setStudentInfo(subClass, firstName, lastName, studentID, DOB);
        studentArrayList.add(subClass);
    }

    //helper method for setting the student information for student course and research (setStudentInfoArrayList)
    //takes sub class of parent Student as a parameter since both classes refer to Student class
    private static void setStudentInfo(Student subClass, String firstName, String lastName, int studentID, String DOB){
        subClass.setFirstName(firstName);
        subClass.setLastName(lastName);
        subClass.setStudentID(studentID);
        subClass.setDOB(DOB);
    }
}
