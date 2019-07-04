/**Willy Alicon
 * Comp585
 * Project2
 *
 * This class is used to traverse a Hardrive's directories, sub-directories, and
 * files. A user specifies a starting directory. From that specified path and
 * onward, the search() method will visit every directory and file along the way.*/
import java.io.File;

import static org.apache.logging.log4j.FormatterLoggerManualExample.logger;

public class Searcher {

    private String root;
    private String path;
    private String search;
    //so we can call its methods
    private SwingThread thread;
    private FinderFrame frame;
    private String replacementString;


    public Searcher(String root, String search, SwingThread thread, FinderFrame frame) {
        this.root = root;
        this.search = search;
        this.thread = thread;
        this.frame = frame;
    }

    public Searcher(String root, String search, SwingThread thread, FinderFrame frame, String replacementString) {
        this.root = root;
        this.search = search;
        this.thread = thread;
        this.frame = frame;
        this.replacementString = replacementString;
    }


    public void search() {

        File folder = new File(root);
        /**Create a list of files from the current path*/
        File[] listOfFiles = folder.listFiles();

        /**If the file is empty, return*/
        if(listOfFiles == null) return;  // Added condition check

        /**For every file, get its absolute path*/
        for (File file : listOfFiles) {
            String path = file.getPath();

            /**if it is a directory, call the search method again so that all its sub directories
             * are listed*/
            if (file.isDirectory()) {
                /**if the user wishes to replace words*/
                if(frame.replacePressed){
                    /**if the thread has not been cancelled keep going through files*/
                    if(!thread.isCancelled()){
                        new Searcher(path, search, thread, frame, replacementString).search();
                    }else{
                        return;
                    }
                }
                /**We are here if the user only wants to find a word.*/
                else{
                    /**Keep reading through the files as long as the thread has not been cancelled*/
                    if(!thread.isCancelled()){
                        new Searcher(path, search,thread,frame).search();
                    }
                    else{
                        return;
                    }
                }
            }
            else{
                logger.info(file.getAbsolutePath());
                /**If we are here it is not a directory*/
//                logger.info(file.getName());
//                logger.info("Replace pressed " + frame.replacePressed);
//                logger.info("Replace is filtered " + replaceIsFiltered());
//                logger.info("replace is allowed " + replaceIsAllowed(file));
//                logger.info("Is on of five " + isOneOfFive(file));

                if(frame.replacePressed){
                    if(thread.replaceIsFiltered()) {
                        /**read only files with selected extension*/
                        if(thread.replaceIsAllowed(file)){
                            thread.findAndReplace(file);
                        }
                    }else{
                        /**read every file as long as it has one of the five
                         * extensions: .java, .cfg, .html, .css, .txt*/
                        if(thread.isOneOfFive(file)){
                            thread.findAndReplace(file);
                        }
                    }
                }
                else{
                    /**If we are here, we do NOT wish to replace words*/
                    if(frame.findPressed){
                        if(thread.findIsFiltered()){
                            /**read only file with selected extension*/
                            if(thread.findingIsAllowed(file)){
                                thread.openFile(file);
                            }
                        }
                        else{
                            /**if we are here. It is not filtered. Read any file
                             * as long as it is one of the five extensions*/
                            if(thread.isOneOfFive(file)){
                                thread.openFile(file);
                            }
                        }
                    }
                }
            }
        }
    }
}