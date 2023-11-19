
/** 
 * Contains the details of the current UI  
 */
public class UIInformation{

    /**
     * userID of the user 
     */
    String userID; 

    /**
     * Camp ID of the camp the user choose 
     */
    String campID; 

    /**
     * Enqury ID of the enqury the user choose
     */
    String enquiryID; 

    /** 
     * Suggestion ID of the suggestion the user choose 
     */
    String suggestionID; 

    /** 
     * the next UIPAGE to run  
     */
    UIPAGE ui; 

    /**
     * Constructor for UIInformation class; 
     */
    public UIInformation(){
        userID = null; 
        campID = null; 
        enquiryID = null; 
        suggestionID = null;
        ui = null; 
    }

    /**
     * getter method for userID 
     * 
     * @return userID 
     */
    public String getUserID(){
        return userID;
    }

    /** 
     * setter method for userID 
     */
    public void setUserID(String userID){
        this.userID = userID;
    }

    /**
     * getter method for campID
     * 
     * @return campID 
     */
    public String getCampID(){
        return campID;
    }

    /** 
     * setter method for campID 
     */
    public void setCampID(String campID){
        this.campID = campID;
    }

    /**
     * getter method for enquiryID 
     * 
     * @return enquryID 
     */
    public String getEnquiryID(){
        return enquiryID;
    }

    /** 
     * setter method for enquryID 
     */
    public void setEnquiryID(String enquiryID){
        this.enquiryID = enquiryID;
    }

    /**
     * getter method for suggestionID 
     * 
     * @return suggestionID 
     */
    public String getSuggestionID(){
        return suggestionID;
    }

    /** 
     * setter method for suggestionID 
     */
    public void setSuggestionID(String suggestionID){
        this.suggestionID = suggestionID;
    }

    /**
     * getter method for UI
     * 
     * @return UI
     */
    public UIPAGE getUIPage(){
        return ui;
    }

    /** 
     * setter method for UI 
     */
    public void setUIPage(UIPAGE ui){
        this.ui = ui;
    }

}
