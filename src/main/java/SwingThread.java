/**Willy Alicon
 * Project2
 * Comp585
 *
 * This class inherits from the SwingWorker class. The class creates a searcher
 * object which traverses all directories, subd directories, and files from a
 * specified path and onward. The class contains methods to check if the file
 * in question is one of .java, .html, .txt, .cfg. or .css. If the file in
 * question is one of the five, the thread will work on it.*/

import org.apache.commons.lang.StringUtils;

import javax.swing.SwingWorker;
import java.io.*;
import java.io.BufferedReader;
import java.lang.reflect.Field;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;

import static org.apache.logging.log4j.FormatterLoggerManualExample.logger;


public class SwingThread<V, S> extends SwingWorker {

    private String root;
    private FinderFrame frame;
    private String path;
    private String search;
    private BufferedReader br;
    private String line;
    private BufferedWriter bw;
    private String replacementString;
    private  String pattern = "";
    private ArrayList<File> roots = new ArrayList<File>();
    private SwingThread reader;
    private Searcher searcher;
    protected int hits;
    private int lineNum;



    public SwingThread(String path, String search, FinderFrame frame){
        this.frame = frame;
        this.path = path;
        this.search = search;
        roots.addAll(Arrays.asList(new File (path)));
    }


    public SwingThread(String path, String search, FinderFrame frame,
                       String replacementString){

        this.frame = frame;
        this.path = path;
        this.search = search;
        this.replacementString = replacementString;
        roots.addAll(Arrays.asList(new File (path)));
    }

    public SwingThread(){

    }

    /**The due in background method calls upon the searcher class.*/
    @Override
    protected List<Object[]> doInBackground(){
            hits = 0;
            lineNum = 0;

            if(isSingleFile()){
                /**Here if the user is interested in reading or chaning a single
                 * file*/
                if(frame.replaceAllPressed()){
                    replaceFileContent(new File (path));
                }
                /**Other wise we are a single file but we only wish to read it*/
                else{
                    displayFileContents(new File(path));
                }
            }
            else{
                /**Here if user is not interested in a single file*/
                if(frame.replacePressed){
                    for(File file: roots){
                        new Searcher(file.toString(), search, this, frame, replacementString).search();
                    }
                }else {
                    for(File file: roots){
                        new Searcher(file.toString(),  search, this, frame).search();
                    }
                }
            }
        return null;
    }


    /**This method is called when the swing worker has completed its
     * task successfully. it is also called if the swing worker was
     * interrupted/cancelled during its task.*/
    @Override
    protected void done() {
        logger.info("Done");
        frame.bar.setVisible(false);
        frame.barTwo.setVisible(false);
        /**If there were no hits during a find OR replace task,
         * display no hits message to user.*/
        if(hits == 0){
            createEmptyTableEntry();
        }
//        else{
////            logger.info("This many hits: " + hits);
//        }
        hits = 0;
        cancel(false);
    }


    /**This method adds a row to the results table stating that no
     * matches were found.*/
    private void createEmptyTableEntry() {
        if(frame.findAllPressed()){
            frame.dtm.addRow(new Object[] { "No hits", "",""});
        }
        else{
            frame.dtmTwo.addRow(new Object[] { "No replacements", "",""});
        }
    }


    /**This method is invoked by the publish method. It receives a list of
     * FileOutput instances. The file output class contains the line number,
     * file name, and the string in which a match was found. This information
     * is used to create the results table.*/
    @Override
    protected void process(List chunks) {
        Object actLineNumber = null;
        Object actFileName = null;
        Object actInString = null;
        for( Object i : chunks){
            Class<?> clazz = i.getClass();
            try{
                Field lineNumber = clazz.getField("lineNumber");
                Field fileName   = clazz.getField("fileName");
                Field inString = clazz.getField("inString");
                actLineNumber = lineNumber.get(i);
                actFileName = fileName.get(i);
                actInString = inString.get(i);
//                logger.debug(actFileName);
            }catch(NoSuchFieldException e){
                e.getStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if(frame.replaceAllPressed()){
                frame.dtmTwo.addRow(new Object[] {actLineNumber.toString(),
                        actFileName.toString(), actInString.toString()});
            }else{
                frame.dtm.addRow(new Object[] {actLineNumber.toString(),
                        actFileName.toString(), actInString.toString()});
            }
        }
    }


    /**Checks if the file in question is on of the five files: java, css,
     * html, cfg, or txt.*/
    protected boolean isOneOfFive(File temp) {
        if(getFileExtension(temp).equals(".java")){
            return true;
        }
        if(getFileExtension(temp).equals(".css")){
            return true;
        }
        if(getFileExtension(temp).equals(".html")){
            return true;
        }
        if(getFileExtension(temp).equals(".cfg")){
            return true;
        }
        if(getFileExtension(temp).equals(".txt")){
            return true;
        }

        return false;
    }

    /**This method returns the file extention of a file.*/
    protected String getFileExtension(File temp){
        String extension = "";

        try{
            if (temp != null && temp.exists()){
                String name = temp.getName();
                extension = name.substring(name.lastIndexOf("."));
            }
        }catch (Exception e){
            extension = "";
        }

        return  extension;
    }

    /**This method checks if the file in question has an extention
     * that matches that of what the user has filtered. Returns true if
     * it does.*/
    protected Boolean replaceIsAllowed(File temp){
        if(frame.javaSelectedTwo && getFileExtension(temp).equals(".java")){
            return true;
        }
        if(frame.cssSelectedTwo && getFileExtension(temp).equals(".css")){
            return true;
        }
        if(frame.htmlSelectedTwo && getFileExtension(temp).equals(".html")){
            return true;
        }
        if(frame.cfgSelectedTwo && getFileExtension(temp).equals(".cfg")){
            return true;
        }
        if(frame.textSelectedTwo && getFileExtension(temp).equals(".txt")){
            return true;
        }

        return false;
    }

    /**We need a variation of the method above because the tabs use DIFFERENT
     * JCheckBoxes. */
    protected Boolean findingIsAllowed(File temp){


        if(frame.javaSelected && getFileExtension(temp).equals(".java")){
//            logger.info("Finding is allowed: "+ temp.getName());
            return true;
        }
        if(frame.cssSelected && getFileExtension(temp).equals(".css")){
//           logger.info("Finding is allowed: "+ temp.getName());

            return true;
        }
        if(frame.htmlSelected && getFileExtension(temp).equals(".html")){
//            logger.info("Finding is allowed: "+ temp.getName());
            return true;
        }
        if(frame.cfgSelected && getFileExtension(temp).equals(".cfg")){
//           logger.info("Finding is allowed: "+ temp.getName());
            return true;
        }
        if(frame.textSelected && getFileExtension(temp).equals(".txt")){
//            logger.info("Finding is allowed: "+ temp.getName());
            return true;
        }
        return false;
    }

    protected Boolean replaceIsFiltered(){
        return frame.filteredReplaceInAll;
    }

    protected Boolean findIsFiltered(){
        return frame.filteredFindInAll;
    }

    protected void displayFileContents(File path) {
        openFile(path);
    }


    /**This method is a wrapper. It used when the path specified by the
     * uses is a path to a single file.*/
    protected void replaceFileContent(File path){
        if(replaceIsFiltered()){
            /**read only files with selected extension*/
            if(replaceIsAllowed(path)){
                findAndReplace(path);
            }
        }else{
            /**If it is not filtered, read only the five extensions*/
            if(isOneOfFive(path)){
                findAndReplace(path);
            }
        }
    }


    /**this method opens a file. It checks if the string specified by user
     * is contained in the file (line by line). If the string is contained, the
     * line number, file name, and the line in which the string is contained is
     * published*/
    protected void openFile(File file){
        int numSubstrings = 0;
        lineNum = 0;
        try{
            logger.info("File: " + file.getName() + " is opened");
            br = new BufferedReader(new FileReader(file));
            while ( (line = br.readLine()) != null){
                lineNum = lineNum + 1;
                if (optionalIsSelected()){
                    /**Both whole case and wordcase are selected*/
                    if(frame.wholeWordSelected && frame.caseSensativeSelected){
                        if(satisfiesBothOptions(line,search)){
                            numSubstrings = ccountNumSubstrings(pattern,line);
                            for(int i = 0; i < numSubstrings; i++ ){
                                publish(new FileOutput (Integer.toString(lineNum), file.getName(), line));
                                hits++;
                            }
                        }
                    }
                    else{
                        /**Only whole word selected*/
                        if(frame.wholeWordSelected){
                            /**remove the first and last space of the word being searched for*/
//                            logger.debug(search);
                            if(satisfiesWholeWord(line,search)){
                                numSubstrings = ccountNumSubstrings(pattern,line);
                                for(int i = 0; i < numSubstrings; i++ ){
                                    publish(new FileOutput (Integer.toString(lineNum), file.getName(), line));
                                    hits++;
                                }
                            }
                        }else{
                            /**match word case is selected */
                            if(line.contains(search)){
                                numSubstrings = StringUtils.countMatches(line,search);
                                for(int i = 0; i < numSubstrings; i++ ){
                                    publish(new FileOutput (Integer.toString(lineNum), file.getName(), line));
                                    hits++;
                                }
                            }
                        }
                    }
                }
                else{
                    /**if we are here, both options are not selected*/
                    if(noOptionSelected(line,search)){
                        numSubstrings = ccountNumSubstrings(pattern,line);
                        for(int i = 0; i < numSubstrings; i++ ){
                            publish(new FileOutput (Integer.toString(lineNum), file.getName(), line));
                            hits++;
                        }
                    }
                }

//                lineNum = lineNum  + 1;
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**this method opens and edits a  file. It checks if the string specified by user
     * is contained in the file (line by line). If the string is contained, the
     * line number, file name, and the line in which the string is contained is
     * published*/
    protected void findAndReplace(File file) {
        int numSubstrings = 0;
        String oldFileName = file.getAbsolutePath();
        String temp = "temp" + file.getName();
        String tempFilePath = file.getAbsolutePath().replace(file.getName(),temp);
        lineNum = 1;
        try{
            br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            bw = new BufferedWriter(new FileWriter(tempFilePath));

            while( (line = br.readLine()) != null){

                /**Do this if whole or case is selected*/
                if (optionalIsSelectedTwo()){
                    /**If optional is selected, then either both are selected or just one*/
                    if(frame.caseSensativeSelectedTwo && frame.wholeWordSelectedTwo){
                        /**if the word is contained replace it*/
                        if(satisfiesBothOptions(line,search)){
//                            numSubstrings = ccountNumSubstrings(pattern, line);
                            line = line.replace(search, replacementString);

//                            publishReplace()
                            publish(new FileOutput (Integer.toString(lineNum), file.getName(), line));
                            hits++;
                        }
                    }
                    /**If we are here its because only one of the optionals is selected*/
                    else{
                        /**if only case-sensative is marked*/
                        if(frame.caseSensativeSelectedTwo){
                            if(line.contains(search)){

                                line = line.replace(search,replacementString);
                                publish(new FileOutput (Integer.toString(lineNum), file.getName(), line));
                                hits++;
                            }
                        }else{
                            /**Only whole word is selected*/
                            if(satisfiesWholeWord(line,search)){
                                line = line.replaceAll(pattern, replacementString);
                                publish(new FileOutput (Integer.toString(lineNum), file.getName(), line));
                                hits++;
                            }
                        }
                    }
                }
                else{
                    /**If none are selected, simply ignore case and whole words*/
                    if(noOptionSelected(line,search)){
                        line = line.replaceAll(pattern, replacementString);
                        publish(new FileOutput (Integer.toString(lineNum), file.getName(), line));
                        hits++;
                    }
                }

                lineNum++;
                bw.write(line);
                bw.newLine();
            }
        }catch (IOException e){
            e.getStackTrace();
        }
        finally {
            try {
                if( br != null){
                    br.close();
                }
            }
            catch (IOException e){
                e.getStackTrace();
            }
            try{
                if(bw != null){
                    bw.close();
                }
            }
            catch (IOException e){
                e.getStackTrace();
            }
        }

        /**Delete the old file. */
        File oldFile = new File(oldFileName);
        oldFile.delete();

        /**Rename the old file to have the name of the old file*/
        File newFile = new File(tempFilePath);
        newFile.renameTo(oldFile);

    }

    protected Boolean noOptionSelected(String source, String subItem){
        subItem = makeLiterals(subItem);
        String pattern = "(?i)" + subItem;
        this.pattern = pattern;
        Pattern p=Pattern.compile(pattern);
        Matcher m=p.matcher(source);
        return m.find();
    }

    /**Satisfies both whole word && case sensative searches*/
    protected Boolean satisfiesBothOptions(String source, String subItem){
        subItem = makeLiterals(subItem);
        if(subItem.trim().equals(subItem)){
            String pattern = "(?<=( |\\t|[^a-zA-Z0-9])|[^a-zA-Z0-9]\\b|^)" + subItem +
                    "(?=( |\\t|[^a-zA-Z0-9])|[^a-zA-Z0-9]\\b|$)";
            this.pattern = pattern;
            Pattern p=Pattern.compile(pattern);
            Matcher m=p.matcher(source);
            return m.find();
        }
        else{
            return false;
        }

    }

    protected Boolean satisfiesWholeWord(String source, String subItem){
        subItem = makeLiterals(subItem);
        if(subItem.trim().equals(subItem)){
            String pattern = "(?i)(?<=( |\\t|[^a-zA-Z0-9])|[^a-zA-Z0-9]\\b|^)" + subItem +
                    "(?=( |\\t|[^a-zA-Z0-9])|[^a-zA-Z0-9]\\b|$)";
            this.pattern = pattern;
            Pattern p=Pattern.compile(pattern);
            Matcher m=p.matcher(source);
            return m.find();
        }else{
            return false;
        }

    }


    protected int ccountNumSubstrings(String pattern, String line){
        int count = 0;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(line);
        while(m.find()){
            count++;
        }
        return count;
    }

    protected String makeLiterals(String subItem){
        char [] temp = subItem.toCharArray();
        String newString = "";

        for(int i = 0; i<temp.length;i++){

            switch ( (int)temp[i]){
                case 92:
                    /**This is for "\" */
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString('\\');
                    break;

                /**This is for "^" */
                case 94:
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString('^');
                    break;
                /**This is for "$" */
                case 36:
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString('$');
                    break;

                /**This is for "{" */
                case 123:
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString('{');
                    break;

                /**This is for "}" */
                case 125:
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString('}');
                    break;

                /**This is for "[" */
                case 91:
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString('[');
                    break;

                /**This is for "]" */
                case 93:
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString(']');
                    break;

                /**This is for "(" */
                case 40:
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString('(');
                    break;


                /**This is for ")" */
                case 41:
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString(')');
                    break;

                /**This is for "." */
                case 46:
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString('.');
                    break;

                /**This is for "*" */
                case 42:
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString('*');
                    break;

                /**This is for "+" */
                case 43:
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString('+');
                    break;

                /**This is for "?" */
                case 63:
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString('?');
                    break;

                /**This is for "|" */
                case 124:
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString('|');
                    break;

                /**This is for "<" */
                case 60:
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString('<');
                    break;

                /**This is for ">" */
                case 62:
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString('>');
                    break;

                /**This is for "-" */
                case 45:
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString('-');
                    break;

                /**This is for "&" */
                case 38:
                    newString = newString + Character.toString('\\' );
                    newString = newString + Character.toString('&');
                    break;

                default: newString = newString + Character.toString(temp[i]);
            }

        }
        return newString;
    }

    public Boolean isSingleFile(){
        File temp = new File (path);
        return temp.isFile();
    }

    protected Boolean optionalIsSelected(){
        return frame.optionalSelected;
    }

    protected Boolean optionalIsSelectedTwo(){
        return frame.optionalSelectedTwo;
    }
}
