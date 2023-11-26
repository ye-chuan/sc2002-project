package scs3grp5.ui.controller;

/**
 * This interface gives the ability for the class to become a controller for a UI
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public interface IUserInterface {

    /** 
     * This method is called to show the main UI of the class
     * 
     * @return the new UI page to run
     */
    public abstract IUserInterface showUI();
}
