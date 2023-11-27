package scs3grp5.ui.controller;


/**
 * This class is a UI controller for the enquiry UI
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class UIInformation{

    /**
     * userID of the user 
     */
    private String userID; 

    /**
     * Camp ID of the camp the user choose 
     */
    private String campID; 

    /**
     * Enqury ID of the enqury the user choose
     */
    private String enquiryID; 

    /** 
     * Suggestion ID of the suggestion the user choose 
     */
    private String suggestionID; 

    /**
     * Boolean value to indicate if user is a staff
     * true if is staff, else student 
     */
    private boolean isStaff; 

    /**
     * Boolean value to indicate if user is a committee of the camp of the current campID stored
     * Both Staff IC and camp committee of this camp is set to true, else false
     */
    private boolean isCommittee; 

    /**
     * Constructor for UIInformation class; 
     */
    public UIInformation(){
        userID = null; 
        campID = null; 
        enquiryID = null; 
        suggestionID = null;
        isCommittee = false; 
        isStaff = false; 
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
     * 
     * @param userID the userID of the user to set
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
     * 
     * @param campID the campID of the camp to set
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
     * 
     * @param enquiryID the enquiryID of the camp to set
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
     * 
     * @param suggestionID the suggestionID of the camp to set
     */
    public void setSuggestionID(String suggestionID){
        this.suggestionID = suggestionID;
    }

    /**
     * getter method for isStaff
     * 
     * @return isStaff
     */
    public boolean getIsStaff(){
        return isStaff;
    }

    /** 
     * setter method for isStaff 
     * 
     * @param isStaff if the user is a staff, true if is staff 
     */
    public void setIsStaff(boolean isStaff){
        this.isStaff = isStaff;
    }

    /**
     * getter method for isCommittee
     * 
     * @return isStaff
     */
    public boolean getIsCommittee(){
        return isCommittee;
    }

    /** 
     * setter method for isStaff 
     * 
     * @param isCommittee if the user is a commitee of a camp, committee means camp committee and staff IC
     */
    public void setIsCommittee(boolean isCommittee){
        this.isCommittee = isCommittee;
    }
}
