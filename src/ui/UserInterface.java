import java.util.Scanner;
import java.util.InputMismatchException;

/** 
 * Represent the general features of the UI class
 * It is to be inherited by other concrete UI class
 */

public abstract class UserInterface{
    
  // ATTRIBUTE 

  /** 
   * userID of the user using the UI
   * 
   * 
   */
  protected UIInformation uiInfo; 

  // METHOD 
  
  /** 
   * Constructor for UI class 
   * 
   * @param uiInfo contains the information of the user 
   */
  public UserInterface(UIInformation uiInfo){
      this.uiInfo = uiInfo; 
  }
  /** 
   * This method is called to show the main UI of the class
   */
  public abstract void showUI();

  /** 
   * This method is to print the list of option 
   * 
   * @return the max number of option 
   */
  protected abstract int printListOfOption(); 

  /**
   * This method helps to get a valid option from the user.
   * At each try, printListOfOption will be called and user will be prompt
   * to key in their input. 
   * 
   * @return the option chosen by the user
   */
  protected int getUserChoiceUI(){
    
    int option;
    int maxOption; 
    
    Scanner sc = new Scanner(System.in); 

    do{
      maxOption = printListOfOption();
      System.out.print("Option choice: "); 
      try{
        option = sc.nextInt();
      }
      catch(InputMismatchException e){
        option = -1;
      }

      if (!this.validOption(option, maxOption)) System.out.println("Invalid Option!");
    } while (!this.validOption(option, maxOption)); 

    sc.close(); 
    return option; 
  }

  /**
   * This method check if the option is a valid option 
   * 
   * @param option Option chosen by the user 
   * @param max the maximum option can take
   * @return True if its a valid option else False
   */
  protected boolean validOption(int option, int max){
    if (option >= 0 && option <= max) return true; 
    return false; 
  }

}