package classify.datacommands;

import classify.user.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


//@@author ParthGandhiNUS
public class DataStorage {
    private static final String NEWLINE = "\n";
    private static final String STUDENT_INFO_FILE_FOLDER = "data/studentInfo";
    private static final String STUDENT_INFO_TEXT_FILE = "data/studentInfo/Student_Information.txt";
    private static final String FAILURE_TO_CREATE_PARENT_FOLDER_DIRECTORY =
            "Failure to create the parent folder directory!";
    private static final String SUCCESSFULLY_UPDATE_STUDENT_RECORDS = "Updated Student Records successfully!";
    private static final String ISSUE_WITH_FOLDER_OR_TEXTFILE = "Issue with directory/text file. Please check!";
    
    
    //@@author ParthGandhiNUS
    /**
     * Accesses the parent directory to try to make the parent directory.
     * Tries to write the lines which are made by the writeStudentInfo Method
     * 
     * @param lines Arraylist containing the processed current tasks in the masterStudentList
     */
    public static void writeStudentInfoFile(List <String> lines){
        try {
            Path studentInfoFolder = Paths.get(STUDENT_INFO_FILE_FOLDER);
            createParentFileFolder(studentInfoFolder);

            FileWriter studentInfoWriter = new FileWriter(STUDENT_INFO_TEXT_FILE);
            for (String line : lines){
                studentInfoWriter.write(line + NEWLINE);
            }

            System.out.println(SUCCESSFULLY_UPDATE_STUDENT_RECORDS);
            Ui.printDivider();
            studentInfoWriter.close();
        } catch (IOException e){
            System.out.println(ISSUE_WITH_FOLDER_OR_TEXTFILE);
        }
    }

    /**
     * Used to create the parent folder for a certain file
     * 
     * @param parentPath    Path file containing the path of folder we want to make the text file in
     * @throws IOException  Triggers whenever the input for the path or creation of the directory is improper
     */
    public static void createParentFileFolder (Path parentPath) throws IOException {
        try{
            Files.createDirectories(parentPath);
        } catch (FileAlreadyExistsException ignored){
            //Ignore this error as this should not cause any issues, we dont want replicas of the same file
        } catch (IOException e){
            System.out.println(FAILURE_TO_CREATE_PARENT_FOLDER_DIRECTORY);
            throw e;
        }
    }
}
