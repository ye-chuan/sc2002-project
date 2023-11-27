package scs3grp5.ui.controller;

import scs3grp5.ui.menu.IPrintMenu;
import scs3grp5.ui.input.ISelectOption;

/**
 * This abstract class is a UI controller for the most of the UI
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public abstract class UserInterface implements IUserInterface{
    
  /** 
   * userID of the user using the UI
   */
  protected UIInformation uiInfo; 

  /**
   * This allow the classes that inherit from IPrintMenu to be able to communicate with this class 
   */
  protected IPrintMenu menu; 

  /**
   * This allow the classes that inherit from ISelectOption to be able to communicate with this class 
   */
  protected ISelectOption optionSelector; 

  /** 
   * Constructor for UI class 
   * 
   * @param uiInfo contains the information of the UI 
   */
  public UserInterface(UIInformation uiInfo){
      this.uiInfo = uiInfo; 
  } 
  
}