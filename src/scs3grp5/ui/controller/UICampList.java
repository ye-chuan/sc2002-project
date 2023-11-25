package scs3grp5.ui.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import scs3grp5.controller.CampListController;
import scs3grp5.controller.CampController;

public class UICampList extends UserInterface{

    /**
     * to communicate with CampListController
     */
    protected CampListController campListCont;

    /**
     * to communicate with CampController
     */
    protected CampController campCont;

    /**
     * contains the listOfCamps to be shown on the UI
     */
    protected ArrayList<String> listOfCamps; 


    /** 
     * Constructor for StudentCampList class 
     * 
     * @param uiInfo contains the information of the user 
     */
    public UICampList(UIInformation uiInfo) {
        super(uiInfo);
        campListCont = new CampListController(); 
        campCont = new CampController(); 
    }

    @Override
    public IUserInterface showUI() {

        IUserInterface filterUI = new UIFilter(uiInfo.getIsStaff());

        int option = -1;
        menu = new MenuStudentCampList(); 
        if (uiInfo.getIsStaff()) menu = new MenuStaffCampList();
        optionSelector = new SelectionMenu();

        boolean wrongInput = false;
        do{
            try{
                ChangePage.changePage();
                option = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                wrongInput = false;
            }
            catch(OptionException e){
                wrongInput = true; 
            }
        }while (wrongInput);

        if (option == 1) campListCont.setDefaultFilter(uiInfo.getIsStaff());
        else if (option == 3){
            if (uiInfo.getIsStaff()){
                uiInfo.setCampID(createCampUI()); // create a camp
                return new UISuperCamp(uiInfo);
            }
            else return new UIHomepage(uiInfo);
        }
        else if (option == 4){
            if (uiInfo.getIsStaff()) return new UIHomepage(uiInfo);
            else return null; 
        }
        else return null;


        wrongInput = false;
        int listOption = -1; 
        do{
            if (option == 1){
                listOfCamps = campListCont.viewCamps();
            }
            else { //option == 2
                listOfCamps = campListCont.viewMyCamp(uiInfo.getUserID());
            }

            menu = new PrintStudentCampList(listOfCamps);
            if (uiInfo.getIsStaff()) menu = new PrintStaffCampList(listOfCamps);
            optionSelector = new SelectionFromList(); 

            try{
                ChangePage.changePage();
                listOption = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
            }
            catch(OptionException e){
                return new UICampList(uiInfo);  
            }
            
            if (listOption == -1) wrongInput = true; 
            else if (listOption == 0) {
                filterUI.showUI();
            }
            else{
                uiInfo.setCampID(listOfCamps.get(listOption-1));
                uiInfo.setIsCommittee(campCont.isCommittee(uiInfo.getUserID(), uiInfo.getCampID())); // this method should work for both student and staff if they are the planner
                if (uiInfo.getIsStaff()){
                    if (uiInfo.getIsCommittee()) return new UISuperCamp(uiInfo); 
                }
                else{
                    if (uiInfo.getIsCommittee()) return new UIPrivilegedCamp(uiInfo); 
                }
                return new UINonPrivilegedCamp(uiInfo);
            }
        }while (wrongInput);
    }

    // use the same filter object each time till we end this function 
    /** 
     * This method provides the UI for user to create a camp
     */
    private String createCampUI(){
      return campCont.create(uiInfo.getUserID());
    }
}
