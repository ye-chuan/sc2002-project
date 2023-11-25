package ui;

/** 
 * Represent the general features of the UI class
 * It is to be inherited by other concrete UI class
 */
public abstract class UserInterface implements IUserInterface{
    
  /** 
   * userID of the user using the UI
   */
  protected UIInformation uiInfo; 

  protected IPrintMenu menu; 
  protected ISelectOption optionSelector; 

  /** 
   * Constructor for UI class 
   * 
   * @param uiInfo contains the information of the user 
   */
  public UserInterface(UIInformation uiInfo){
      this.uiInfo = uiInfo; 
  } 
  
}