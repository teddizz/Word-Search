/**Willy Alicon
 * Comp585
 * Project2
 *
 * This class implements the ItemListener. It is used to listen to the
 * JCheckBoxes that allow the user to filter files java, txt, css, hmtl or
 * cfg. There is also listeners for whole word searches and case
 * sensitive searches. */
import javax.swing.JCheckBox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CheckBoxHandler extends JCheckBox implements ItemListener {
    FinderFrame frame;

    public CheckBoxHandler (String name, FinderFrame frame){
        super(name);
        addItemListener(this);
        this.frame = frame;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        if(frame.textCheckBox.isSelected() ){
            frame.textSelected = true;
        }else{
            frame.textSelected = false;
        }

        if(frame.cfgCheckBox.isSelected()){
            frame.cfgSelected = true;
        }else{
            frame.cfgSelected = false;
        }

        if(frame.htmlCheckBox.isSelected()){
            frame.htmlSelected = true;
        }else{
            frame.htmlSelected = false;
        }

        if(frame.cssCheckBox.isSelected()){
            frame.cssSelected = true;
        }else{
            frame.cssSelected = false;
        }


        if(frame.javaCheckBox.isSelected()){
            frame.javaSelected = true;
        }else{
            frame.javaSelected = false;
        }


        if(frame.wholeWordCheckBox.isSelected()){
            frame.wholeWordSelected = true;
        }else{
            frame.wholeWordSelected = false;
        }
        if(frame.caseWordCheckBox.isSelected()){
            frame.caseSensativeSelected = true;
        }else{
            frame.caseSensativeSelected = false;
        }

        setFiltered();
//            System.out.println("FindingFiltered: "+ frame.filteredFindInAll);
//            System.out.println("text: "+ frame.textSelected + " css: "+ frame.cssSelected + " html: " + frame.htmlSelected);
        setOptional();

        /**These are the checkBoxes for tab replace in files */
        if(frame.textCheckBoxTwo.isSelected() ){
            frame.textSelectedTwo = true;
        }else{
            frame.textSelectedTwo = false;
        }

        if(frame.cfgCheckBoxTwo.isSelected()){
            frame.cfgSelectedTwo = true;
        }else{
            frame.cfgSelectedTwo = false;
        }

        if(frame.htmlCheckBoxTwo.isSelected()){
            frame.htmlSelectedTwo = true;
        }else{
            frame.htmlSelectedTwo = false;
        }

        if(frame.cssCheckBoxTwo.isSelected()){
            frame.cssSelectedTwo = true;
        }else{
            frame.cssSelectedTwo = false;
        }

        if(frame.javaCheckBoxTwo.isSelected()){
            frame.javaSelectedTwo = true;
        }else{
            frame.javaSelectedTwo = false;
        }

        /**This is for the case and whole word options*/
        if(frame.wholeWordCheckBoxTwo.isSelected()){
            frame.wholeWordSelectedTwo = true;
        }else{
            frame.wholeWordSelectedTwo = false;
        }
        if(frame.caseWordCheckBoxTwo.isSelected()){
            frame.caseSensativeSelectedTwo = true;
        }else{
            frame.caseSensativeSelectedTwo = false;
        }

        setFilteredTwo();
        setOptionalTwo();
    }

    private void setOptionalTwo() {
        if(frame.wholeWordSelectedTwo || frame.caseSensativeSelectedTwo){
            frame.optionalSelectedTwo = true;
        }
        else{
            frame.optionalSelectedTwo = false;
        }
    }

    private  void setOptional(){
        if(frame.wholeWordSelected || frame.caseSensativeSelected){
            frame.optionalSelected = true;
        }
        else{
            frame.optionalSelected = false;
        }
    }

    private void setFiltered(){
        if(frame.textSelected || frame.cfgSelected || frame.htmlSelected ||
                frame.cssSelected || frame.javaSelected) {
            frame.filteredFindInAll = true;
        }else{
            frame.filteredFindInAll = false;
        }
    }

    private void setFilteredTwo(){
        if(frame.textSelectedTwo || frame.cfgSelectedTwo || frame.htmlSelectedTwo ||
                frame.cssSelectedTwo || frame.javaSelectedTwo) {
            frame.filteredReplaceInAll = true;
        }else{
            frame.filteredReplaceInAll = false;
        }
    }

}