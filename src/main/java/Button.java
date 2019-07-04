/**Willy Alicon
 * Comp 585
 * Project2
 *
 * This class listens for when a user clicks on the find all button or the
 * find and replace all button. */

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.apache.logging.log4j.FormatterLoggerManualExample.logger;

public class Button extends JButton implements ActionListener {

    FinderFrame frame;
    private int returnVal;

    public Button(String title, FinderFrame frame){
        super(title);
        this.frame = frame;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        /**if the user has pressed on the find all button*/
        if(source == frame.findAllButton){
            /**check if the find what field is empty. If it is, notify the user.*/
            if(frame.findWhatTextField.getText().equals("")){
                JOptionPane.showMessageDialog(frame,"Find what: is empty");
            }
            else{
                frame.setFindPressed(true);
                frame.updateStringSearch(frame.findWhatTextField.getText());
                /**If the find what field is not empty, check if the path field is empty.*/
                if(frame.filePath == null || frame.filePath.equals("") || !validatePath(frame.filePath) ){
                    JOptionPane.showMessageDialog(frame,"Choose a valid file or directory path");
                }
                else{
                    frame.bar.setVisible(true);
                    frame.bar.setIndeterminate(true);
                    frame.findWhatTextFieldTwo.setText(frame.findWhatTextField.getText());
                    frame.createFindAllWorker();
                }
            }
        }

        /**Open the file chooser when the file chooser button is pressed.*/
        if( source == frame.open){
            returnVal = openFC();
            setAbsolutePath(returnVal);
            frame.thePath.setText(frame.filePath);
            frame.thePathTwo.setText(frame.filePath);
        }


        if (source == frame.findAndReplace){
            /**Check if the the findwhat field is empty*/
            if(frame.findWhatTextFieldTwo.getText().equals("")){
                JOptionPane.showMessageDialog(frame, "Find what is Empty");
            }else{
                /**Check if the user has not specified a path to a file or a directory. If
                 * the user has not specified anything, throw them a warning*/
                if( frame.filePath == null || frame.filePath.equals("") || !validatePath(frame.filePathTwo)){
                    JOptionPane.showMessageDialog(frame,"Choose a valid file or directory path");
                }else{
                    int dialogResult = JOptionPane.showConfirmDialog (frame,
                            "Are you sure you want to replace all?","Warning",
                            JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        frame.barTwo.setIndeterminate(true);
                        frame.barTwo.setVisible(true);
                        frame.setReplacedPressed(true);
                        frame.findWhatTextField.setText(frame.findWhatTextFieldTwo.getText());
                        frame.updateStringSearch(frame.findWhatTextFieldTwo.getText());
                        frame.updateStringReplace(frame.replaceTextField.getText());
                        frame.createReplaceALlWorker();
                    }
                }
            }
        }
        if(source == frame.open2){
            returnVal = openFC();
            setAbsolutePathTwo(returnVal);
            frame.thePathTwo.setText(frame.filePath);
            frame.thePath.setText(frame.filePath);
        }
    }

    private Boolean validatePath(String path) {
        File file = new File(path);
        if(file.isDirectory() || file.exists()){
            return true;
        }
        return false;
    }

    private void setAbsolutePathTwo(int returnVal) {
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File file = frame.fc.getSelectedFile();
            frame.filePathTwo = file.getAbsolutePath();
            frame.filePath = file.getAbsolutePath();
        }

    }

    private int openFC(){
        return frame.fc.showOpenDialog(frame);
    }

    private void setAbsolutePath(int returnVal){
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File file = frame.fc.getSelectedFile();
            frame.filePath = file.getAbsolutePath();
            frame.filePathTwo = file.getAbsolutePath();
        }
    }
}
