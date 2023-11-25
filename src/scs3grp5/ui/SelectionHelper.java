package scs3grp5.ui;

public class SelectionHelper {
    
    /**
     * This method check if the option is a valid option 
     * 
     * @param option Option chosen by the user 
     * @param  min the minimum option can take
     * @param  max the maximum option can take
     * @return True if its a valid option else False
    */
    public static boolean validOption(int option, int min,  int max){
        if (option >= min && option <= max) return true; 
        return false;   
    }

    public static boolean comfirmChanges(){
        System.out.println("\t(Press 0 to "); 
        System.out.println("\t(2) No"); 

        Scanner sc = new Scanner(System.in); 
        
    }
}
