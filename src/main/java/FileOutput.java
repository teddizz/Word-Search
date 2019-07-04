/**Willy Alicon
 * Comp585
 * Project2
 *
 * This class is used to create a FileOutput class which
 * stores the line number, the text file's name, and the string
 * in which the string being searched for is found.*/
public class FileOutput {
    public String lineNumber;
    public String fileName;
    public String inString;

    public FileOutput(String lineNum, String fileName, String inString){
        this.lineNumber = lineNum;
        this.fileName = fileName;
        this.inString = inString;
    }

    public String toString(){
        String fullString = lineNumber + "\n" +
                fileName + "\n" + inString;
        return fullString;
    }

    public String getLineNum(){
        return lineNumber;
    }
}
