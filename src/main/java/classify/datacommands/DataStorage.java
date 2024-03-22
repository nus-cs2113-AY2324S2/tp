package classify.datacommands;

public class DataStorage {

    /**
     * Used to create the parent folder for a certain file
     * 
     * @param parentPath    Path file containing the path of folder we wanna make the text file in
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
