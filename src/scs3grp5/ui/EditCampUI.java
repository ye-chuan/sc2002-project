package ui;

public class EditCampUI extends UserInterface {


    /**
     * to communicate with CampController
     */
    private CampController campCont; 
    private String name; 
    private String date;
    private String closingDate;
    private int participantSlot;
    private int campCommSlot;
    private boolean faculty;
    private String description;
    private boolean visibility; 

    /** 
     * Constructor for CreateEditCampUI class 
     * 
     * @param uiInfo contains the information of the user  
     */
    public EditCampUI(UIInformation uiInfo) {
        super(uiInfo);
        campCont = new CampController(); 
        setInitialInfo();

    }

    @Override
    public void showUI() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showUI'");
    }

    @Override
    protected int printListOfOption() {
        System.out.println("----------------------------------------------"); 
        System.out.println("CAMP EDIT MENU");
        System.out.println("(1) Camp Name: " + name); 
        System.out.println("(2) Date of Camp: " + date);
        System.out.println("(3) Registration closing date: " + closingDate);
        System.out.println("(4) Participants Slot: " + participantSlot); 
        System.out.println("(5) Camp Committee Slot: " + campCommSlot); 
        System.out.println("(6) Faculty: " + faculty); 
        System.out.println("(7) Camp Description: " + description); 
        System.out.println("(8) Visibility: " + visibility);
        
        return 8; 
    }

    private void setInitialInfo(){
        name = campCont.getName(uiInfo.getCampID()); 
        date = campCont.getDate(uiInfo.getCampID());
        closingDate = campCont.getClosingDate(uiInfo.getCampID());
        participantSlot = campCont.getTotalParticipantSlot(uiInfo.getCampID());
        campCommSlot = campCont.getTotalCampCommSlot(uiInfo.getCampID());
        faculty = campCont.getFaculty(uiInfo.getCampID());
        description = campCont.getDescription(uiInfo.getCampID());
        visibility = campCont.getVisibility(uiInfo.getCampID()); 
    }


    
}


/*
 * System.out.println("Please input the details of the camp");
        System.out.print("Camp Name: "); 
        String _input = sc.nextLine(); 
        campCont.changeName(campID, _input); 

        System.out.print("Camp Start Date: "); 
        _input = sc.nextLine();
        campCont.changeDates(campID, _input); 

        System.out.print("Camp End Date: "); 
        _input = sc.nextLine(); 
        campCont.changeEndDate(campID, _input); 

        System.out.print("Camp Registration Closing Date: "); 
        _input = sc.nextLine(); 
        campCont.changeClosingDate(campID, _input); 

        System.out.print("Camp Location: "); 
        _input = sc.nextLine(); 
        campCont.changeLocation(campID, _input); 

        System.out.print("Number of Participants: "); 
        int intInput = sc.nextInt(); 
        campCont.changeCampParticipantSlots(campID, intInput); 

        System.out.print("Number of Committee: "); 
        intInput = sc.nextInt(); 
        campCont.changeCampCommSlots(campID, intInput);

        System.out.println("Camp Target Audience");
        System.out.println("(1) Open to whole NTU"); 
        System.out.println("(2) Open only to faculty"); 
        System.out.print("option: "); 
        intInput = sc.nextInt(); 
        if (intInput == 1) campCont.changeFaculty(campID, false); 
        else campCont.changeFaculty(campID, true); 

        System.out.print("Camp Description: "); 
        _input = sc.nextLine(); 

        System.out.println("Camp Created Successfully!!"); 
 */