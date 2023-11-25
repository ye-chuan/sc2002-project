package ui;

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
        System.out.println("To comfirm changes:"); 
        System.out.println("\t(1) Yes"); 
        System.out.println("\t(2) No"); 

        Scanner sc = new Scanner(System.in); 
        
    }
}
