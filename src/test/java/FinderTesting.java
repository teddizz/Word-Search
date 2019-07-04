import org.junit.Test;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FinderTesting {
    SwingThread worker;
    Boolean temp;
    String file;

    public FinderTesting(){

    }

    /**Tests to see if the getFileExtension() method returns the correct file extension*/
    @Test
    public void returnsCorrectFileExtension(){
        worker = new SwingThread();
        file = worker.getFileExtension(new File("C:\\Users\\Willy\\Desktop\\Project2TestCases\\new.cfg"));
        assertEquals(".cfg", file);
    }

    /**Tests the makeLiterals() method to check if all the regular expression meta symbols are
     * escaped */
    @Test
    public void metaSymbolsEscaped(){
        SwingThread worker = new SwingThread();
        file = worker.makeLiterals("^${}[]().*+?|<>-&");
        assertEquals("\\^\\$\\{\\}\\[\\]\\(\\)\\.\\*\\+\\?\\|\\<\\>\\-\\&",file);
    }

    /**Tests the makeLiterals() method to check if all the regular expression meta symbols are
     * escaped */
    @Test
    public void metaSymbolsEscapedWithEmptyString(){
        SwingThread worker = new SwingThread();
        file = worker.makeLiterals("");
        assertEquals("",file);
    }

    /**Tests the makeLiterals() method to check if all the regular expression meta symbols are
     * escaped */
    @Test
    public void metaSymbolsEscapedWithWords(){
        SwingThread worker = new SwingThread();
        file = worker.makeLiterals("^hello<head> </head>?");
        assertEquals("\\^hello\\<head\\> \\</head\\>\\?",file);
    }

    /**Tests that the program allows txt files to be opened*/
    @Test
    public void allowsTextFilesToBeOpened(){
        SwingThread worker = new SwingThread();
        temp = worker.isOneOfFive(new File("C:\\Users\\Willy\\Desktop\\Project2TestCases\\CtrlShiftTest.txt"));
        assertTrue(temp);
    }

    /**Tests that the program allows cfg files to be opened*/
    @Test
    public void allowsCFGFilesToBeOpened(){
        SwingThread worker = new SwingThread();
        temp = worker.isOneOfFive(new File("C:\\Users\\Willy\\Desktop\\Project2TestCases\\new.cfg"));
        assertTrue(temp);
    }

    /**Tests that the program allows java files to be opened*/
    @Test
    public void allowsJavaFilesToBeOpened(){
        SwingThread worker = new SwingThread();
        temp = worker.isOneOfFive(new File("C:\\Users\\Willy\\Desktop\\Project2TestCases\\CtrlShiftFTest3.java"));
        assertTrue(temp);
    }

    /**Tests that the program allows java files to be opened*/
    @Test
    public void allowsHTMLFilesToBeOpened(){
        SwingThread worker = new SwingThread();
        temp = worker.isOneOfFive(new File("C:\\Users\\Willy\\Desktop\\Project2TestCases\\Hello\\Awards.html"));
        assertTrue(temp);
    }

    /**Tests that the program allows java files to be opened*/
    @Test
    public void allowsCSSFilesToBeOpened(){
        SwingThread worker = new SwingThread();
        temp = worker.isOneOfFive(new File("C:\\Users\\Willy\\Desktop\\Project2TestCases\\Hello\\project1.css"));
        assertTrue(temp);
    }

    /**Tests that the program does NOT allow other files other than the five above*/
    @Test
    public void doesNotAllowsXlSXFileToBeOpened(){
        SwingThread worker = new SwingThread();
        temp = worker.isOneOfFive(new File("C:\\Users\\Willy\\Desktop\\Project2TestCases\\CtrlShiftFTest2.xlsx"));
        assertFalse(temp);
    }

    /**Tests that the program does NOT allow other files other than the five above*/
    @Test
    public void doesNotAllowsPDFileToBeOpened(){
        SwingThread worker = new SwingThread();
        temp = worker.isOneOfFive(new File("C:\\Users\\Willy\\Desktop\\Project2TestCases\\Project 2 Requirements.pdf"));
        assertFalse(temp);
    }

    /**This test checks that the word can is not returned as a result if the line contains
     * the word Scan*/
    @Test
    public void unsatisiesWholeWord(){
        SwingThread worker = new SwingThread();
        temp = worker.satisfiesWholeWord("Scan", "can");
        assertFalse(temp);
    }

    /**This method checks that the word sCAn is returned as a result if the line contains
     * the word scan. We are only check for a whole word, we dont care about case sensativity
     */
    @Test
    public void satisfiesWholeWordCaseInsensative(){
        SwingThread worker = new SwingThread();
        temp = worker.satisfiesWholeWord("Go and scan me some papers", "sCAn");
        assertTrue(temp);
    }

    @Test
    public void satisfiesWholeAndCase(){
        SwingThread worker = new SwingThread();
        temp = worker.satisfiesBothOptions("Go and scan me some papers", "scan");
        assertTrue(temp);
    }

    /**scan is a whole word, but because case sensitivity has also been selected, this method will
     * return false*/
    @Test
    public void doesNotSatisfyWholeAndCase(){
        SwingThread worker = new SwingThread();
        temp = worker.satisfiesBothOptions("Go and scan me some papers", "sCAn");
        assertFalse(temp);
    }

    /**Since whole word is not selected and case sensativity is no selected, the substring
     * cA should be found. It is part of the word scan*/
    @Test
    public void satisfiesNoWholeandCase(){
        SwingThread worker = new SwingThread();
        temp = worker.noOptionSelected("Go and scan me some papers", "cA");
        assertTrue(temp);
    }

}
