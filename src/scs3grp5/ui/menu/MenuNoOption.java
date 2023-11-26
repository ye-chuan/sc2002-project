package scs3grp5.ui.menu;

/**
 * This class is provides the menu when there is no menu. 
 * This class helps us to view details as long as the user want before
 * they want to change UI page. 
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class MenuNoOption implements IPrintMenu {

    /** 
     * This method is to print the list of option 
     * 
     * @return the max number of option 
    */
    @Override
    public int printMenu() {
        System.out.println("(press any key to go back)");
        return 0; 
    }
    
}
