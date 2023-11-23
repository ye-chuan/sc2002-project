package ui;

/**
 * This interface gives the ability to be able to print a list of items and select from it 
 */
public interface IListUi {
    
    /**
     * This methods prints the list of items 
     */
    public void printList(); 
    
    /**
     * This methods provides the UI for user to choose from the printed list 
     * 
     * @return the index of the item from the list 
     */
    public String selectFromListUI();
}
