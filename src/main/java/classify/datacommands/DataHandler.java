package classify.datacommands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import classify.student.Student;
import classify.student.StudentList;
import classify.user.Ui;

//@@author ParthGandhiNUS
public class DataHandler {
    private static final String ANALYSING_INPUT_MESSAGE = "Analysing Inputs...";
    private static final String STUDENT_INFO_FILE_FOLDER = "data/studentInfo";
    private static final String STUDENT_INFO_TEXT_FILE = "data/studentInfo/Student_Information.txt";
    private static final String CREATE_NEW_STUDENT_INFO_FILE = "Creating a new Student Info File...";
    private static final String CREATE_NEW_STUDENT_INFO_FILE_SUCCESS = "Created a new student info file successfully!";
    private static final String CREATE_NEW_STUDENT_INFO_FILE_FAILURE = "Unable to create a new student info file!";
    private static final String RETRIEVING_PREVIOUS_STUDENT_DATA = "Now getting previous Student Info Records...";

    //@@author ParthGandhiNUS
    /**
     * Function which is called to generate an arrayList "lines" which updates according to the users' inputs.
     * Calls the writeStudentInfoFile function to update Student_Information.txt
     * 
     * @param newList ArrayList containing the current students
     */
    public static void writeStudentInfo(List <Student> newList){
        List<String> lines = new ArrayList<>();
        for (Student student:newList){
            lines.add(student.textFileInputString());
        }
        System.out.println(ANALYSING_INPUT_MESSAGE);
        DataStorage.writeStudentInfoFile(lines);
    }

    /**
     * This accesses Student_Information.txt and calls the restoreStudentList function
     * 
     * @param currentTask   ArrayList containing the current students
     * @throws IOException  When unable to get the Student_Information.txt file or has any input errors
     */
    public static void readStudentInfo(List <Student> newList) throws IOException{
        try{
            DataStorage.createParentFileFolder(Paths.get(STUDENT_INFO_FILE_FOLDER));
            Path studentInfoFilePath = Paths.get(STUDENT_INFO_TEXT_FILE);
            if (!Files.exists(studentInfoFilePath)){
                System.out.println(CREATE_NEW_STUDENT_INFO_FILE);
                Files.createFile(studentInfoFilePath);
            }
            
            FileReader fileReader = new FileReader(STUDENT_INFO_TEXT_FILE);
            BufferedReader line = new BufferedReader(fileReader);
            System.out.println(RETRIEVING_PREVIOUS_STUDENT_DATA);

            while (line.ready()){
                DataReader.restoreStudentList(line.readLine(), StudentList.masterStudentList);
            }
            
            System.out.println(CREATE_NEW_STUDENT_INFO_FILE_SUCCESS);
            Ui.printDivider();
            line.close();
        } catch (IOException e) {
            System.out.println(CREATE_NEW_STUDENT_INFO_FILE_FAILURE);
            throw e;
        }
    }
}
