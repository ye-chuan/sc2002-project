
/**
 * This interface gives the class the ability to provide a UI thats print and select from a ArrayList
 */
public interface IListUI {

    /** 
     * This method prints the list
     */
    public void printList(); 

    /** 
     * This method provides the UI for user to select from the list printed 
     */
    public String selectFromListUI();
}
