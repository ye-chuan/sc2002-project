package ui;

import java.util.Scanner;
import java.util.InputMismatchException;

/** 
 * Represent the general features of the UI class
 * It is to be inherited by other concrete UI class
 */

public abstract class UserInterface implements IUserInterface{
    
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
    boolean wrongInput = false; 
    
    Scanner sc = new Scanner(System.in); 

    do{
      ChangePage.changePage();
      maxOption = printListOfOption();
      if (wrongInput){
        System.out.println("Invalid Option!");
        System.out.println("Select a valid option: ");
      }
      else System.out.print("Select option: "); 
      try{
        option = sc.nextInt();
      }
      catch(InputMismatchException e){
        option = -1;
      }
      wrongInput = true; 
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

  protected String fillUpSpace(String string, int numberOfSpace, int minNumberOfSpaceOnBothEnd, boolean centralise){

    String stringWithSpaces = "";
    int stringLength = string.length(); 
    int whiteSpaceToAdd = numberOfSpace - stringLength - 2*minNumberOfSpaceOnBothEnd;
    
    // Add the minimum number of space at the front
    for (int i=0; i<minNumberOfSpaceOnBothEnd; i++){
      stringWithSpaces += " "; 
    }
    if (whiteSpaceToAdd < 0){
      stringWithSpaces += string.substring(0, numberOfSpace - 2*minNumberOfSpaceOnBothEnd - 3); 
      stringWithSpaces += "..."; 
    }
    else{
      int whiteSpaceFront = 0;
      int whiteSpaceBack; 
    

      if (centralise){
        whiteSpaceFront = whiteSpaceToAdd / 2; 
        for (int i=0; i<whiteSpaceFront;i++) stringWithSpaces += " ";
      }

      stringWithSpaces += string; 
      whiteSpaceBack = whiteSpaceToAdd - whiteSpaceFront;

      for (int i=0;i<whiteSpaceBack;i++) stringWithSpaces += " ";
    }

    // add the minimum number of space at the back
    for (int i=0; i<minNumberOfSpaceOnBothEnd; i++){
      stringWithSpaces += " "; 
    }

    return stringWithSpaces; 

}

}