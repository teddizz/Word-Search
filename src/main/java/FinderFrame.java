/**Willy Alicon
 * Comp585
 * Project2
 *
 * This class is used to initialize all the componenets needed to create the GUI.
 * The components consist of: buttons, checkboxes, panels, text fields, and Jtable.
 * The class contains helper methods to update the the text fields (such as when
 * a user chooses a path from the file chooser).
 *
 * The border layout was used to layout the components for this project.
 * */
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class FinderFrame extends JFrame {

    protected Preferences pref;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;

    protected JTextField directoryTextField;
    protected JTextField findWhatTextField;
    protected JTextField findWhatTextFieldTwo;
    protected JTextField replaceTextField;

    private JLabel findWhatLabel;
    private JLabel findWhatLabelTwo;
    private JLabel directoryLabel;
    private JLabel directoryLabelTwo;
    private JLabel filter;
    private JLabel filter2;
    private JLabel replaceWithLabel;
    protected JLabel findAndReplaceLabel;
    protected JLabel resultsLabel;
    protected JLabel noHits;
    protected JPanel findInAllDirectoryPanel;
    protected JPanel replaceInAllDirectoryPanel;
    protected JPanel radioButtonsPanel;
    protected JPanel findInAllPanel;
    protected JPanel replaceInAllPanel;
    protected JPanel findInAllDirectoryPanelOne;
    protected JPanel findInAllDirectoryPanelTwo;
    protected JPanel buttonPaneWrapper;
    protected JPanel buttonPaneWrapperTwo;
    protected JPanel buttonPaneWrapperNorth;
    protected JPanel buttonPaneWrapperNorthTwo;
    protected JPanel buttonPaneWrapperSouth;
    protected JPanel buttonPaneWrapperSouthTwo;
    protected JPanel buttonPaneOne;
    protected JPanel buttonPaneOneTwo;
    protected JPanel buttonPaneTwo;
    protected JPanel buttonPaneTwoTwo;
    protected JPanel buttonPaneThree;
    private JPanel replaceInAllDirectoryPanelWrapper;
    private JPanel contentWrapperOne;
    private JPanel pathDirectoryPane;
    private JPanel findWhatPane;
    private JPanel replaceWithPane;
    private JPanel contentWrapperTwo;
    private JPanel resultsPanel;
    private JPanel resultsPanelTwo;
    private JComboBox box;
    private JComboBox boxTwo;
    private JComboBox boxThree;

    private ArrayList<String> findSearchesList = new ArrayList<String>();
    private ArrayList<String> findSearchesListTwo = new ArrayList<String>();
    private ArrayList<String> replaceList = new ArrayList<String>();
    private int findInAllSearchesCounter = 0;
    private int replaceCounter = 0;
    private ArrayList<String> findInAllSearches = new ArrayList<String>();
    private ArrayList<String> findInAllSearchesTwo = new ArrayList<String>();
    private  final static String [] findInAllKeys = {"s1","s2","s3","s4",
            "s5", "s6", "s7", "s8", "s9", "s10"};
    private  final static String [] findInAllKeysTwo = {"s11","s12","s13","s14",
            "s15", "s16", "s17", "s18", "s19", "s20"};
    private  final static String [] replaceInAllKeys = {"s21","s22","s23","s24",
            "s25", "s26", "s27", "s28", "s29", "s30"};
    private  final static String [] coloumnNames = {"Line number", "File Name", "String"};

    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel southPanelTwo;
    private JPanel frameWrpper;
    protected JButton findAllButton;
    protected JCheckBox textCheckBox;
    protected JCheckBox textCheckBoxTwo;
    protected JCheckBox cfgCheckBox;
    protected JCheckBox cfgCheckBoxTwo;
    protected JCheckBox javaCheckBox;
    protected JCheckBox javaCheckBoxTwo;
    protected JCheckBox htmlCheckBox;
    protected JCheckBox htmlCheckBoxTwo;
    protected JCheckBox cssCheckBox;
    protected JCheckBox cssCheckBoxTwo;
    protected JCheckBox wholeWordCheckBox;
    protected JCheckBox wholeWordCheckBoxTwo;
    protected JCheckBox caseWordCheckBox;
    protected JCheckBox caseWordCheckBoxTwo;

    protected SwingWorker<Void,String> worker;

    private JTabbedPane tabbedPane;

    private final static int FRAME_WIDTH = 800;
    private final static int FRAME_HEIGHT = 700;
    private final static String FALSEE = "false";
    private final static int TEXT_FIELD_COLOUMNS = 30;

    protected JFileChooser fc = null;
    protected JButton open;
    protected JButton open2;
    protected JButton cancel;
    protected JButton findAndReplace;
    protected String filePath ="";
    protected String filePathTwo ="";

    protected Boolean replacePressed = false;
    protected Boolean findPressed = false;
    protected Boolean filteredFindInAll = false;
    protected Boolean filteredReplaceInAll = false;
    protected Boolean optionalSelected = false;
    protected Boolean optionalSelectedTwo = false;
    /**Might have to be changed with the log thing*/
    protected Boolean textSelected = false;
    protected Boolean cfgSelected = false;
    protected Boolean htmlSelected = false ;
    protected Boolean cssSelected  = false;
    protected Boolean javaSelected = false ;
    protected Boolean textSelectedTwo = false;
    protected Boolean cfgSelectedTwo = false;
    protected Boolean htmlSelectedTwo = false;
    protected Boolean cssSelectedTwo = false;
    protected Boolean javaSelectedTwo = false;
    protected Boolean caseSensativeSelected = false;
    protected Boolean caseSensativeSelectedTwo =false;
    protected Boolean wholeWordSelected = false;
    protected Boolean wholeWordSelectedTwo = false;

    /**for the find all tab*/
    private String idText;
    private String idCFG;
    private String idHTML;
    private String idCSS;
    private String idJava;
    private String idWholeWord;
    private String idCaseSensative;
    private String idCounter;
    private String idfilePath;
    /**For the replace in all tab*/
    private String idTextTwo;
    private String idCFGTwo;
    private String idHTMLTwo;
    private String idCSSTwo;
    private String idJavaTwo;
    private String idWholeWordTwo;
    private String idCaseSensativeTwo;
    private String idReplaceCounter;
    protected String idfilePathTwo;
    protected JTable table;
    protected DefaultTableModel dtm;
    private JScrollPane scrollPane;
    protected JTable tableTwo;
    protected JScrollPane scrollPaneTwo;
    protected DefaultTableModel dtmTwo;
    protected Button cancelTwo;
    private SwingThread<List<Object[]>, String> workerTwo;
    protected JTextField thePath;
    protected JTextField thePathTwo;
    protected JProgressBar bar;
    protected JPanel barWrapper;
    protected JProgressBar barTwo;


    FinderFrame(){
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                storePreferences();
                e.getWindow().dispose();
            }
        });
        initializeIDS();
        initializeComponents();
        addJComboBoxListtener();
        //addListeners();
        createBorderLayout();
        setDirectory();
        setTitleFC();
        setSelectionModeFC();

        buildPanel();
        buildFrame();

        initializePreference();
        pack();
    }

    private void initializeComponents() {
        initializeTextFields();
        initializeLabels();
        initializeButtons();
        initializePanel();
        initializeFileChooser();
        initializeTabs();
        initializeCheckBoxes();
        initializeJComboBox();
        initializeTable();
        initializeBar();
        initializeMenuBar();
    }


    private void buildFrame(){
        tabbedPane.addTab("Find in files", findInAllPanel);
        tabbedPane.addTab("Replace in files", replaceInAllPanel);
        add(tabbedPane);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void buildPanel() {
        /**Building the Find in Files panel*/
        findInAllDirectoryPanelOne.add(directoryLabel);
        findInAllDirectoryPanelOne.add(thePath);
        findInAllDirectoryPanelOne.add(open);
        findInAllDirectoryPanelTwo.add(findWhatLabel);
        findInAllDirectoryPanelTwo.add(findWhatTextField);
        findInAllDirectoryPanelTwo.add(box);
        findInAllDirectoryPanel.add(findInAllDirectoryPanelOne,BorderLayout.NORTH);
        findInAllDirectoryPanel.add(findInAllDirectoryPanelTwo,BorderLayout.SOUTH);
        northPanel.add(findInAllDirectoryPanel,BorderLayout.NORTH);
        findInAllPanel.add(northPanel, BorderLayout.NORTH);
        findInAllPanel.add(southPanel, BorderLayout.SOUTH);
        buttonPaneOne.add(filter);
        buttonPaneOne.add(textCheckBox);
        buttonPaneOne.add(cfgCheckBox);
        buttonPaneOne.add(javaCheckBox);
        buttonPaneOne.add(htmlCheckBox);
        buttonPaneOne.add(cssCheckBox);
        buttonPaneTwo.add(wholeWordCheckBox);
        buttonPaneTwo.add(caseWordCheckBox);
        buttonPaneWrapperNorth.add(buttonPaneOne, BorderLayout.NORTH);
        buttonPaneWrapperNorth.add(buttonPaneTwo, BorderLayout.SOUTH);
        buttonPaneWrapper.add(buttonPaneWrapperNorth,BorderLayout.NORTH);
        buttonPaneThree.add(findAllButton);
        buttonPaneThree.add(cancel);
        buttonPaneWrapperSouth.add(buttonPaneThree, BorderLayout.SOUTH);
        buttonPaneWrapper.add(buttonPaneWrapperSouth, BorderLayout.SOUTH);
        northPanel.add(buttonPaneWrapper,BorderLayout.SOUTH);
        resultsPanel.add(scrollPane);
        southPanel.add(resultsPanel, BorderLayout.NORTH);
        southPanel.add(bar, BorderLayout.SOUTH);


        /**Building the Replace in Files panel*/
        pathDirectoryPane.add(directoryLabelTwo);
        pathDirectoryPane.add(thePathTwo);
        pathDirectoryPane.add(open2);
        findWhatPane.add(findWhatLabelTwo);
        findWhatPane.add(findWhatTextFieldTwo);
        findWhatPane.add(boxTwo);
        contentWrapperOne.add(pathDirectoryPane,BorderLayout.NORTH);
        contentWrapperOne.add(findWhatPane,BorderLayout.SOUTH);
        replaceWithPane.add(replaceWithLabel);
        replaceWithPane.add(replaceTextField);
        replaceWithPane.add(boxThree);
        buttonPaneOneTwo.add(filter2);
        buttonPaneOneTwo.add(textCheckBoxTwo);
        buttonPaneOneTwo.add(cfgCheckBoxTwo);
        buttonPaneOneTwo.add(javaCheckBoxTwo);
        buttonPaneOneTwo.add(htmlCheckBoxTwo);
        buttonPaneOneTwo.add(cssCheckBoxTwo);
        buttonPaneTwoTwo.add(wholeWordCheckBoxTwo);
        buttonPaneTwoTwo.add(caseWordCheckBoxTwo);
        buttonPaneWrapperSouthTwo.add(findAndReplace);
        buttonPaneWrapperSouthTwo.add(cancelTwo);
        buttonPaneWrapperNorthTwo.add(buttonPaneOneTwo,BorderLayout.NORTH);
        buttonPaneWrapperNorthTwo.add(buttonPaneTwoTwo, BorderLayout.SOUTH);
        buttonPaneWrapperTwo.add(buttonPaneWrapperNorthTwo,BorderLayout.NORTH);
        buttonPaneWrapperTwo.add(buttonPaneWrapperSouthTwo,BorderLayout.SOUTH);
        contentWrapperTwo.add(replaceWithPane,BorderLayout.NORTH);
        contentWrapperTwo.add(buttonPaneWrapperTwo,BorderLayout.SOUTH);
        replaceInAllDirectoryPanelWrapper.add(contentWrapperOne,BorderLayout.NORTH);
        replaceInAllDirectoryPanelWrapper.add(contentWrapperTwo,BorderLayout.SOUTH);
        replaceInAllPanel.add(replaceInAllDirectoryPanelWrapper, BorderLayout.NORTH);
        resultsPanelTwo.add(scrollPaneTwo);
        southPanelTwo.add(resultsPanelTwo,BorderLayout.NORTH);
        southPanelTwo.add(barTwo);
        replaceInAllPanel.add(southPanelTwo, BorderLayout.SOUTH);

        addMenuBar();

    }

    /**This method initializes the ID's that will be used to store the
     * user's preferences.*/
    private void initializeIDS() {
        /**for the find all tab*/
        idText = "idText";
        idCFG = "idCFG";
        idHTML = "idHTML";
        idCSS = "idCSS";
        idJava = "idJava";
        idWholeWord = "idWholeWord";
        idCaseSensative = "idCaseSensative";
        idCounter = "idCounter";
        idfilePath = "idFilePath";

        /**For the replace in all tab*/
        idTextTwo = "idTextTwo";
        idCFGTwo = "idCFGTwo";
        idHTMLTwo = "idHTMLTwo";
        idCSSTwo = "idCSSTwo";
        idJavaTwo = "idJavaTwo";
        idWholeWordTwo = "idWholeWordTwo";
        idCaseSensativeTwo = "idCaseSensativeTwo";
        idReplaceCounter = "idReplaceCounter";
        idfilePathTwo = "idFilePathTwo";

    }

    /**This method retrieves the users preferences and autocompletes
     * the settings of the user*/
    private void initializePreference() {
        pref = Preferences.userRoot().node(this.getClass().getName());
        try{
            /**if the preferences are not empty, load them. Else do nothing*/
            if( !(pref.keys().length == 0)){
                /**Loading the JCheckBoxes for the first tab*/
                findWhatTextField.setText(pref.get(findInAllKeys[0], ""));
                textCheckBox.setSelected(pref.getBoolean(idText, Boolean.parseBoolean(FALSEE)));
                cfgCheckBox.setSelected(pref.getBoolean(idCFG, Boolean.parseBoolean(FALSEE)));
                htmlCheckBox.setSelected(pref.getBoolean(idHTML, Boolean.parseBoolean(FALSEE)));
                cssCheckBox.setSelected(pref.getBoolean(idCSS, Boolean.parseBoolean(FALSEE)));
                javaCheckBox.setSelected(pref.getBoolean(idJava, Boolean.parseBoolean(FALSEE)));
                wholeWordCheckBox.setSelected(pref.getBoolean(idWholeWord, Boolean.parseBoolean(FALSEE)));
                caseWordCheckBox.setSelected(pref.getBoolean(idCaseSensative, Boolean.parseBoolean(FALSEE)));
                thePath.setText(pref.get(idfilePath,""));
                filePath = pref.get(idfilePath,"");

                findInAllSearchesCounter  = pref.getInt(idCounter,Integer.parseInt("0"));
                for(int i = 0; i < findInAllSearchesCounter; i++){
                    findInAllSearches.add(pref.get(findInAllKeys[i], ""));
                    findSearchesList.add(pref.get(findInAllKeys[i],""));
                }
                box.setModel(new DefaultComboBoxModel(findInAllSearches.toArray()));


                /**Loading the JCheckBoxes for the first tab*/
                findWhatTextFieldTwo.setText(pref.get(findInAllKeysTwo[0],""));
                textCheckBoxTwo.setSelected(pref.getBoolean(idTextTwo, Boolean.parseBoolean(FALSEE)));
                cfgCheckBoxTwo.setSelected(pref.getBoolean(idCFGTwo, Boolean.parseBoolean(FALSEE)));
                htmlCheckBoxTwo.setSelected(pref.getBoolean(idHTMLTwo, Boolean.parseBoolean(FALSEE)));
                cssCheckBoxTwo.setSelected(pref.getBoolean(idCSSTwo, Boolean.parseBoolean(FALSEE)));
                javaCheckBoxTwo.setSelected(pref.getBoolean(idJavaTwo, Boolean.parseBoolean(FALSEE)));
                wholeWordCheckBoxTwo.setSelected(pref.getBoolean(idWholeWordTwo, Boolean.parseBoolean(FALSEE)));
                caseWordCheckBoxTwo.setSelected(pref.getBoolean(idCaseSensativeTwo, Boolean.parseBoolean(FALSEE)));
                thePathTwo.setText(pref.get(idfilePathTwo,""));
                filePathTwo = pref.get(idfilePathTwo,"");

                for(int i = 0; i < findInAllSearchesCounter; i++){
                    findInAllSearchesTwo.add(pref.get(findInAllKeysTwo[i], ""));
                    findSearchesListTwo.add(pref.get(findInAllKeysTwo[i],""));
                }

                boxTwo.setModel(new DefaultComboBoxModel(findInAllSearchesTwo.toArray()));

                replaceTextField.setText(pref.get(replaceInAllKeys[0], ""));

                replaceCounter = pref.getInt(idReplaceCounter, Integer.parseInt("0"));
                for(int i = 0; i < replaceCounter; i++){
                    replaceList.add(pref.get(replaceInAllKeys[i], ""));
                }
                boxThree.setModel(new DefaultComboBoxModel(replaceList.toArray()));

            }
        }catch (BackingStoreException e){
            e.printStackTrace();
        }
    }


    /**This method is called once the user attempts to exit the application
     * The method stores the user's settings. The saved settings are used to
     * initialize the users' preferences at start up.*/
    private void storePreferences(){
        pref.putBoolean(idText, textSelected);
        pref.putBoolean(idCFG, cfgSelected);
        pref.putBoolean(idHTML, htmlSelected);
        pref.putBoolean(idCSS, cssSelected);
        pref.putBoolean(idJava, javaSelected);
        pref.putBoolean(idWholeWord, wholeWordSelected);
        pref.putBoolean(idCaseSensative, caseSensativeSelected);
        pref.put(idfilePath, filePath);

        for(int i = 0; i < findInAllSearchesCounter; i++ ){
            pref.put(findInAllKeys[i], findSearchesList.get(i));
        }

        pref.putBoolean(idTextTwo, textSelectedTwo);
        pref.putBoolean(idCFGTwo, cfgSelectedTwo);
        pref.putBoolean(idHTMLTwo, htmlSelectedTwo);
        pref.putBoolean(idCSSTwo, cssSelectedTwo);
        pref.putBoolean(idJavaTwo, javaSelectedTwo);
        pref.putBoolean(idWholeWordTwo, wholeWordSelectedTwo);
        pref.putBoolean(idCaseSensativeTwo, caseSensativeSelectedTwo);
        pref.put(idfilePathTwo, filePathTwo);

        for(int i = 0; i < findInAllSearchesCounter; i++ ){
            pref.put(findInAllKeysTwo[i], findSearchesListTwo.get(i));
        }
        /**counter for the find all searches*/
        pref.putInt(idCounter, findInAllSearchesCounter);

        /**counter for the replace*/
        pref.putInt(idReplaceCounter,replaceCounter);

        for(int i = 0; i < replaceCounter; i++){
            pref.put(replaceInAllKeys[i], replaceList.get(i));
        }

    }


    /**This method filters the files a user can chooser from to the
     * five following files" java, css, html, cfg, and txt*/
    private void setSelectionModeFC() {
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("All Files",
                "html", "css", "txt", "cfg", "java"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("HTML", "html"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("CSS","css"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Text","txt"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("CFG", "cfg"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Java","java"));
        fc.setAcceptAllFileFilterUsed(false);

    }

    public void addMenuBar(){
        setJMenuBar(menuBar); /**Sets the menu bar to the frame*/
        menuBar.add(menu);
        menu.add(menuItem);

    }

    /**Initializes the progress bar. Sets its visibility
     * to false*/
    private void initializeBar() {
        bar = new JProgressBar();
        bar.setVisible(false);
        barTwo = new JProgressBar();
        barTwo.setVisible(false);
    }

    private void initializeTable() {
        table = new JTable();
        dtm = new DefaultTableModel(0,0);
        table.setPreferredScrollableViewportSize(new Dimension(760, 300));
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        //add header to the table model
        dtm.setColumnIdentifiers(coloumnNames);
        //set model into the table object
        table.setModel(dtm);

        tableTwo = new JTable();
        dtmTwo = new DefaultTableModel(0,0);
        tableTwo.setPreferredScrollableViewportSize(new Dimension(760, 300));
        tableTwo.setFillsViewportHeight(true);
        scrollPaneTwo = new JScrollPane(tableTwo);
        //add header to the table model
        dtmTwo.setColumnIdentifiers(coloumnNames);
        //set model into the table object
        tableTwo.setModel(dtmTwo);

    }

    private void initializeMenuBar(){
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menuItem = new JMenuItem("Exit");
        addExitListner();
    }

    public void addExitListner(){
        menuItem.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        storePreferences();
                        System.exit(0);
                    }
                }
        );
    }

    private void initializeJComboBox() {
        box = new JComboBox();
        boxTwo = new JComboBox();
        boxThree = new JComboBox();
    }

    private void addJComboBoxListtener() {
        box.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        /**when  previous word that was searched for is clicked
                         * on, fill up the Findwhat textfield with that word*/
                        if(e.getStateChange() == ItemEvent.SELECTED){
                            findWhatTextField.setText(findSearchesList.get(box.getSelectedIndex()));
                            findWhatTextFieldTwo.setText(findWhatTextField.getText());
                            boxTwo.setSelectedIndex(box.getSelectedIndex());
                        }
                    }
                }
        );

        boxTwo.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if(e.getStateChange() == ItemEvent.SELECTED){
                            /**when  previous word that was searched for is clicked
                             * on, fill up the Findwhat textfield with that word*/
                            findWhatTextFieldTwo.setText(findSearchesListTwo.get(boxTwo.getSelectedIndex()));
                            findWhatTextField.setText(findWhatTextFieldTwo.getText());
                            box.setSelectedIndex(boxTwo.getSelectedIndex());
                        }
                    }
                }
        );

        boxThree.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        /**when the previous word that was replaced is clicked
                         * on, fill up the replace-with textfield with that word*/
                        if(e.getStateChange() == ItemEvent.SELECTED){
                            replaceTextField.setText(replaceList.get(boxThree.getSelectedIndex()));
                        }
                    }
                }
        );
    }

    protected void updateStringSearch(String temp){
        if(findInAllSearchesCounter < findInAllKeys.length){
            /**if the list doesnt contain the word being searched add it to the list. Else
             * do nothing*/
            if (!findSearchesList.contains(temp)){
                findSearchesList.add(0, temp);
                findSearchesListTwo.add(0, temp);

                box.setModel(new DefaultComboBoxModel(findSearchesList.toArray()));
                boxTwo.setModel(new DefaultComboBoxModel(findSearchesListTwo.toArray()));
                findInAllSearchesCounter++;
            }
            else{
                /**If it is already contained. Delete it and then add it to the beginning*/
                findSearchesList.remove(temp);
                findSearchesList.add(0, temp);
                findSearchesListTwo.remove(temp);
                findSearchesListTwo.add(0,temp);

                box.setModel(new DefaultComboBoxModel(findSearchesList.toArray()));
                boxTwo.setModel(new DefaultComboBoxModel(findSearchesListTwo.toArray()));
            }
        }
        /**the counter is greater than the size of the array. We are at max elements*/
        else{
            findInAllSearchesCounter = 10;
            if (!findSearchesList.contains(temp)){
                findSearchesList.add(0, temp);
                findSearchesListTwo.add(0, temp);
                box.setModel(new DefaultComboBoxModel(findSearchesList.toArray()));
                boxTwo.setModel(new DefaultComboBoxModel(findSearchesListTwo.toArray()));
            }
            else{
                findSearchesList.remove(temp);
                findSearchesList.add(0,temp);
                findSearchesListTwo.remove(temp);
                findSearchesListTwo.add(0,temp);

                box.setModel(new DefaultComboBoxModel(findSearchesList.toArray()));
                boxTwo.setModel(new DefaultComboBoxModel(findSearchesListTwo.toArray()));
            }
        }
    }


    /**This method is used to add a word that the user has previously
     * looked for to the drop down list. */
    protected void updateStringReplace(String temp){
        if(replaceCounter < replaceInAllKeys.length){
            if( !replaceList.contains(temp)){
                replaceList.add(0,temp);
                boxThree.setModel(new DefaultComboBoxModel(replaceList.toArray()));
                replaceCounter++;
            }
            /**if item is already on the list*/
            else{
                replaceList.remove(temp);
                replaceList.add(0,temp);
                boxThree.setModel(new DefaultComboBoxModel(replaceList.toArray()));
            }
        }
        else{
            replaceCounter = 10;
            if( !replaceList.contains(temp)){
                replaceList.add(0,temp);
                boxThree.setModel(new DefaultComboBoxModel(replaceList.toArray()));
            }
            /**if item is already on the list*/
            else{
                replaceList.remove(temp);
                replaceList.add(0,temp);
                boxThree.setModel(new DefaultComboBoxModel(replaceList.toArray()));
            }
        }
    }

    /**Initializes the JCheckBoxes*/
    private void initializeCheckBoxes() {
        textCheckBox = new CheckBoxHandler(".txt",this);
        cfgCheckBox = new CheckBoxHandler(".cfg",this);
        javaCheckBox = new CheckBoxHandler(".java",this);
        htmlCheckBox = new CheckBoxHandler(".html",this);
        cssCheckBox = new CheckBoxHandler(".css",this);
        wholeWordCheckBox = new CheckBoxHandler("Match whole word",this);
        caseWordCheckBox = new CheckBoxHandler("Match word case",this);

        textCheckBoxTwo = new CheckBoxHandler(".txt",this);
        cfgCheckBoxTwo = new CheckBoxHandler(".cfg",this);
        javaCheckBoxTwo = new CheckBoxHandler(".java",this);
        htmlCheckBoxTwo = new CheckBoxHandler(".html",this);
        htmlCheckBoxTwo = new CheckBoxHandler(".html",this);
        cssCheckBoxTwo = new CheckBoxHandler(".css",this);
        wholeWordCheckBoxTwo = new CheckBoxHandler("Match whole word",this);
        caseWordCheckBoxTwo = new CheckBoxHandler("Match word case",this);
    }

    private void initializeTabs() {
        tabbedPane = new JTabbedPane();
    }

    private void setTitleFC(){
        fc.setDialogTitle("File Chooser");
    }

    private void setDirectory(){
        fc.setCurrentDirectory(new java.io.File("user.home"));
    }
    private void initializeFileChooser() {
        Boolean old = UIManager.getBoolean("FileChooser.readOnly");
        UIManager.put("FileChooser.readOnly",Boolean.TRUE);
        fc = new JFileChooser();
        UIManager.put("FileChooser.readOnly", old);
    }

    private void initializePanel() {
        findInAllPanel = new JPanel();
        findInAllDirectoryPanel = new JPanel();
        replaceInAllPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();
        replaceInAllDirectoryPanel = new JPanel();
        findInAllDirectoryPanelOne = new JPanel();
        findInAllDirectoryPanelTwo = new JPanel();
        buttonPaneWrapper  = new JPanel();
        buttonPaneWrapperTwo = new JPanel();
        buttonPaneWrapperNorth = new JPanel();
        buttonPaneWrapperSouth = new JPanel();
        buttonPaneOne = new JPanel();
        buttonPaneTwo = new JPanel();
        buttonPaneThree = new JPanel();
        replaceInAllDirectoryPanelWrapper = new JPanel();
        contentWrapperOne = new JPanel();
        pathDirectoryPane = new JPanel();
        findWhatPane = new JPanel();
        replaceWithPane = new JPanel();
        contentWrapperTwo = new JPanel();
        buttonPaneWrapperNorthTwo = new JPanel();
        buttonPaneOneTwo = new JPanel();
        buttonPaneTwoTwo = new JPanel();
        buttonPaneWrapperSouthTwo = new JPanel();
        resultsPanel = new JPanel();
        southPanel = new JPanel();
        resultsPanelTwo = new JPanel();
        southPanelTwo = new JPanel();
        resultsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Results Area"));
        resultsPanelTwo.setBorder(new TitledBorder(new EtchedBorder(), "Results Area"));
        radioButtonsPanel = new JPanel();
        barWrapper = new JPanel();
        frameWrpper = new JPanel();
    }

    private void createBorderLayout() {
        findInAllPanel.setLayout(new BorderLayout());
        replaceInAllPanel.setLayout(new BorderLayout());
        northPanel.setLayout(new BorderLayout());
        findInAllDirectoryPanel.setLayout(new BorderLayout());
        buttonPaneWrapper.setLayout(new BorderLayout());
        buttonPaneWrapperTwo.setLayout(new BorderLayout());
        buttonPaneWrapperNorth.setLayout(new BorderLayout());
        replaceInAllDirectoryPanelWrapper.setLayout(new BorderLayout());
        contentWrapperOne.setLayout(new BorderLayout());
        contentWrapperTwo.setLayout(new BorderLayout());
        buttonPaneWrapperNorthTwo.setLayout(new BorderLayout());
        southPanel.setLayout(new BorderLayout());
        southPanelTwo.setLayout(new BorderLayout());
        frameWrpper.setLayout(new BorderLayout());
    }

    private void initializeButtons() {
        findAllButton = new Button("Find All", this);
        open = new Button("...", this);
        findAndReplace = new Button("Find And Replace All",this);
        open2 = new Button("...", this);
        cancel = new Button("Cancel",this);
        cancelTwo = new Button("Cancel",this);

    }

    private void initializeLabels() {
        findWhatLabel = new JLabel("Find what: ");
        directoryLabel = new JLabel("Path: ");
        resultsLabel = new JLabel("_____");
        findAndReplaceLabel = new JLabel("Replace with: ");
        filter = new JLabel("Filters: ");
        filter2 = new JLabel("Filters: ");
        findWhatLabelTwo = new JLabel("Find What:");
        directoryLabelTwo = new JLabel("Path: ");
        replaceWithLabel = new JLabel("Replace with: ");
        noHits = new JLabel("adfkajdfjkadfkafd");

    }

    private void initializeTextFields() {
        directoryTextField = new JTextField(TEXT_FIELD_COLOUMNS);
        directoryTextField.setText("");
        findWhatTextField = new JTextField(TEXT_FIELD_COLOUMNS);
        findWhatTextField.setText("");
        replaceTextField = new JTextField(TEXT_FIELD_COLOUMNS);
        replaceTextField.setText("");
        findWhatTextFieldTwo = new JTextField(TEXT_FIELD_COLOUMNS);
        findWhatTextFieldTwo.setText("");
        thePath = new JTextField(TEXT_FIELD_COLOUMNS);
        thePath.setEditable(false);
        thePathTwo = new JTextField(TEXT_FIELD_COLOUMNS);
        thePathTwo.setEditable(false);
    }

    protected void setResultLabel(String text){
        resultsLabel.setText(text);
    }

    /**Here make sure that if the user is to cancel the file chooser. that the
     * program dont crash.
     *
     * also take care of the case where a file is not selected and the user
     * just preses the find all button. I.E the user doesnt ever press on the
     * file chooser button*/

    protected void createFindAllWorker(){

        worker = new SwingThread<List<Object[]>,String>(obtainFilePath(),
                findWhatTextField.getText(), this/*,createFinder()*/) {
            @Override
            protected List<Object[]> doInBackground() {
                return super.doInBackground();
            }

            @Override
            protected void done() {
                super.done();
            }

            @Override
            protected void process(List chunks) {
                super.process(chunks);
            }

        };

        addCancelListener();
        clearTable();

        worker.execute();

    }

    private void clearTable() {
        dtm.setRowCount(0);
    }

    protected void createReplaceALlWorker(){
        workerTwo = new SwingThread<List<Object[]>,String>(obtainFilePathTwo(),
                findWhatTextFieldTwo.getText(), this, replaceTextField.getText()) {

            @Override
            protected List<Object[]> doInBackground(){
                return super.doInBackground();
            }

            @Override
            protected void done() {
                super.done();
            }

            @Override
            protected void process(List chunks) {
                super.process(chunks);
            }

        };

        addCancelListenerTwo();
        clearTableTwo();
        workerTwo.execute();
    }

    private void clearTableTwo() {
        dtmTwo.setRowCount(0);
    }

    private void addCancelListenerTwo() {
        cancelTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workerTwo.cancel(true);
            }
        });
    }


    private String obtainFilePath(){
        return filePath;
    }

    private String obtainFilePathTwo(){
        return filePathTwo;
    }

    protected Boolean replaceAllPressed(){
        return replacePressed == true;
    }

    protected Boolean findAllPressed(){
        return findPressed == true;
    }

    protected void setReplacedPressed(Boolean temp){
        replacePressed = temp;
        findPressed = !temp;
    }

    protected void setFindPressed(Boolean temp){
        findPressed = temp;
        replacePressed = !temp;
    }
    protected void addCancelListener() {
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worker.cancel(true);
            }
        });
    }
}
