package scs3grp5.ui.menu;

public class MenuNoOption implements IPrintMenu {

    @Override
    public int printMenu() {
        System.out.println("(press any key to go back)");
        return 0; 
    }
    
}
