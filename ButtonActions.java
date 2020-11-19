// Emily Griscom
// IDE: NetBeans IDE 8.2 RC
// I've added heavy comments for people that have trouble with this project

package gui; // change this to your package's name
import java.io.*;

/*  Call "createXML" to read directory
    and try to create that XML file. 
    Example: this directory
    C:\Users\YourName\YourFolder\NewXML.xml
    will create NewXML.xml within C:\Users\YourName\YourFolder. */
public class ButtonActions {
    // status of create attempt
    public final static int NO_ERROR = 0,
                            FOLDER_NOT_EXIST = 1,
                            FOLDER_NOT_DIRECTORY = 2,
                            NOT_XML = 3,
                            XML_ALREADY_EXISTS = 4,
                            FAILURE_TO_CREATE = 5;
    
    /*  dirStr is a directory.
        This method will read dirStr
        and create the XML file in an existing folder.
        The value returned is the status,
        or whatever the file was successfully
        created or not. */
    public static int createXML(String dirStr){  
        // convert argument into a file
        File dirFile = new File(dirStr);
                
        // check if folder of file is valid
        File dirFolder = new File(getFolder(dirStr));
        if(!dirFolder.exists())
            return FOLDER_NOT_EXIST;
        if(!dirFolder.isDirectory())
            return FOLDER_NOT_DIRECTORY;
        if(!isXML(dirStr)) // folder is valid, is the XML name valid?
            return NOT_XML;
        if(dirFile.exists()) // do not overwrite existing file
            return XML_ALREADY_EXISTS;
        try { // create newCase.xml in directory that user typed
            dirFile.createNewFile();
            // NOTE: This does not read the file!
            // add your "read XML" code here
            return NO_ERROR;
        } catch (IOException ex) {
            return FAILURE_TO_CREATE;
        }
    }
    
    /*  True iff String str is rather empty or
    only contains whitespace.   */
    public static boolean isBlank(String str) {
    	if(str.trim().isEmpty())
    		return true;
    	return false;
    }
    
    
    /*  Return the folder's name of the argument as a string.
        Ex: C:\Users\YourName\FolderYouWantToUse\YourXML.xml
        returns C:\Users\YourName\FolderYouWantToUse */
    private static String getFolder(String fileDir) {
        int fileNameIndex = fileDir.lastIndexOf("\\"); // index of "\"
        if(fileNameIndex == -1) // if no backslash exists, quit now
            return "";
        // prune everything after last backslash, return
        return fileDir.substring(0, fileNameIndex);
    }
    
    
    /*  Argument is a directory in string form.
        Return false if it is not a .xml file. */
    static boolean isXML(String dirStr) {
        // get file name and its extension
        // Ex: C:\Users\YourName\FolderYouWantToUse\YourXML.xml
        // becomes YourXML.xml and .xml
        String fileName = dirStr.substring(dirStr.lastIndexOf("\\") + 1);
        int periodIndex = fileName.lastIndexOf(".");
        if(periodIndex == -1) // was there even a period?
            return false;
        String fileExtension = fileName.substring(periodIndex);
        return fileExtension.equals(".xml"); // is the file extension ".xml?"
    }
}