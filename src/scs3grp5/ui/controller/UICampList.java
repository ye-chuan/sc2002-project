package scs3grp5.ui.controller;

import java.util.List;
import scs3grp5.controller.CampListController;
import scs3grp5.ui.input.SelectionFromList;
import scs3grp5.ui.input.SelectionMenu;
import scs3grp5.ui.menu.ListStaffCamp;
import scs3grp5.ui.menu.ListStudentCamp;
import scs3grp5.ui.menu.MenuStaffCampList;
import scs3grp5.ui.menu.MenuStudentCampList;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.OptionException;
import scs3grp5.ui.ulti.PrintHelper;
import scs3grp5.controller.CampController;


/**
 * This class is a UI controller for the CampList UI
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
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
    protected List<String> listOfCamps; 


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

    /**
     * This method helps to get a valid option from the user.
     * At each try, printListOfOption will be called and user will be prompt
     * to key in their input. 
     * 
     * @return the option chosen by the user
    */

    /**
     * This method controls the logic for the UI for the the camp list UI
     * 
     * @return the new UI page to run 
     */
    @Override
    public IUserInterface showUI() {

        IUserInterface filterUI = new UIFilter(uiInfo.getUserID(), uiInfo.getIsStaff());

        int option = -1;
        menu = new MenuStudentCampList(); 
        if (uiInfo.getIsStaff()) menu = new MenuStaffCampList();
        optionSelector = new SelectionMenu();

        boolean wrongInput = false;
        do{
            try{
                ChangePage.changePage();
                System.out.println(PrintHelper.LOGO_STRING);
                System.out.println();
                option = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                wrongInput = false;
            }
            catch(OptionException e){
                wrongInput = true; 
            }
        }while (wrongInput);

        if (option == 3){
            if (uiInfo.getIsStaff()){
                uiInfo.setCampID(createCampUI()); // create a camp
                return new UIEditCamp(uiInfo, true);
            }
            else return new UIHomepage(uiInfo);
        }
        else if (option == 4){
            if (uiInfo.getIsStaff()) return new UIHomepage(uiInfo);
            else return null; 
        }
        else if (option == 0)return null;

        campListCont.setDefaultFilter(uiInfo.getUserID(), uiInfo.getIsStaff());
        wrongInput = false;
        boolean finishFilter = false; 
        int listOption = -1; 
        do{
            if (option == 1){
                listOfCamps = campListCont.viewCamps();
            }
            else { //option == 2
                listOfCamps = campListCont.viewMyCamp(uiInfo.getUserID());
            }

            
            if (uiInfo.getIsStaff()) menu = new ListStaffCamp(listOfCamps);
            else menu = new ListStudentCamp(listOfCamps);
            optionSelector = new SelectionFromList(); 

            try{
                ChangePage.changePage();
                listOption = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                wrongInput = false; 
            }
            catch(OptionException e){
                return new UICampList(uiInfo); 
            }
            
            if (listOption == -1) wrongInput = true; 
            else if (listOption == 0) {
                filterUI.showUI();
                finishFilter = true; 
                wrongInput = false; 
            }
            else{
                uiInfo.setCampID(listOfCamps.get(listOption-1));
                uiInfo.setIsCommittee(campCont.isCommittee(uiInfo.getUserID(), uiInfo.getCampID())); // this method should work for both student and staff if they are the planne
                if (uiInfo.getIsStaff()){
                    if (uiInfo.getIsCommittee()) return new UISuperCamp(uiInfo); 
                }
                else{
                    if (uiInfo.getIsCommittee()) return new UIPrivilegedCamp(uiInfo); 
                }
                return new UINonPrivilegedCamp(uiInfo);
            }
        }while (wrongInput || finishFilter);
        return new UICampList(uiInfo);
    }

    /** 
     * This method provides allows the UI for user to create a camp
     * 
     * @return new campID for the newly created camp
     */
    private String createCampUI(){
      return campCont.create(uiInfo.getUserID());
    }
}
