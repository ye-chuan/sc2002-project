package scs3grp5.ui.menu;

public class MenuStudentCampList implements IPrintMenu{

    @Override
    public int printMenu() {
        int option = 0; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55  
        System.out.println("CAMP LIST MENU"); 
        System.out.printf("\t(%d) View All Camps\n", ++option); 
        System.out.printf("\t(%d) View My Camps\n", ++option);
        System.out.printf("\t(%d) Go to Home Page\n", ++option); 
        System.out.printf("\t(0) Exit Program\n");
        System.out.println("-------------------------------------------------------"); 
        return option; 
    }
    
}
