package ui;

public class PrintHelper {
    // ANSI escape codes for text colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    /**
     * This method is static and returns the string with the necessary amount of space 
     * to format the printing of the UI 
     * 
     * @param string the initial string to print 
     * @param numberOfSpace the total number of space that the return string should have 
     * @param minNumberOfSpaceOnBothEnd the minimum number of space at both extreme end of the string 
     * @param centralise true if the string should be centralise in the center else the string should be at the left most 
     * @return the string with the appropriate amount of spaces 
     */
    public static String fillUpSpace(String string, int numberOfSpace, int minNumberOfSpaceOnBothEnd, boolean centralise){

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
