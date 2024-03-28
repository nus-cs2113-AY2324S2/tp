package classify.datacommands;

import classify.student.Student;
import classify.user.InputParsing;
import classify.user.Ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author ParthGandhiNUS
public class DataReader {
    private static final Logger logger = Logger.getLogger(InputParsing.class.getName());

    public static Scanner in = new Scanner(System.in);
    

    //@@author ParthGandhiNUS
    /**
     * Method restores the previous student list by accessing the
     * line-by-line information in the Student_Information.txt
     * 
     * @param studentFileInput  Line-by-line information of strings in the Student_Information.txt
     * @param masterStudentList The list of all students
     */
    public static void restoreStudentList(String studentFileInput, ArrayList <Student> masterStudentList ){
        
        if (studentFileInput != null){
            if (InputParsing.findStudentByName(masterStudentList, studentFileInput) != null) {
                assert InputParsing.findStudentByName(masterStudentList, studentFileInput) != null;
                logger.log(Level.WARNING, "Student with the same name already exists.");
                
                Ui.printSameNameError();
                Ui.printDivider();
                return;
            }

            Student student = new Student(studentFileInput);
        
            masterStudentList.add(student);
            logger.log(Level.INFO, "Student added successfully.");
            Ui.printStudentAdded();
            Ui.printDivider();
        }
    }
    
}


