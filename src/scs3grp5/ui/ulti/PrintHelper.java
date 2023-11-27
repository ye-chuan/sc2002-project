package scs3grp5.ui.ulti;

import java.util.ArrayList;


/** 
 * A class that contains static strings constants and static prints functions 
 * to be used throughout the UI program
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class PrintHelper {

    /**
     * Application logo 
     */
    public static String LOGO_STRING = "\r\n" + //
        "                                                  \r\n" + //
        "                                                  \r\n" + //
        "  .g8\"\"\"bgd     db      `7MMM.     ,MMF' .M\"\"\"bgd \r\n" + //
        ".dP'     `M    ;MM:       MMMb    dPMM  ,MI    \"Y \r\n" + //
        "dM'       `   ,V^MM.      M YM   ,M MM  `MMb.     \r\n" + //
        "MM           ,M  `MM      M  Mb  M' MM    `YMMNq. \r\n" + //
        "MM.          AbmmmqMA     M  YM.P'  MM  .     `MM \r\n" + //
        "`Mb.     ,' A'     VML    M  `YM'   MM  Mb     dM \r\n" + //
        "  `\"bmmmd'.AMA.   .AMMA..JML. `'  .JMML.P\"Ybmmd\"  \r\n" + //
        "                                                  \r\n" + //
        "                                                  \r\n" + //
        "";

      /**
       * End of program logo
       */
      public static String END_PROGRAM = "\r\n" + //
          "888~~  Y88b    / 888 ~~~888~~~ 888 888b    |  e88~~\\  \r\n" + //
          "888___  Y88b  /  888    888    888 |Y88b   | d888     \r\n" + //
          "888      Y88b/   888    888    888 | Y88b  | 8888 __  \r\n" + //
          "888      /Y88b   888    888    888 |  Y88b | 8888   | \r\n" + //
          "888     /  Y88b  888    888    888 |   Y88b| Y888   | \r\n" + //
          "888___ /    Y88b 888    888    888 |    Y888  \"88__/  \r\n" + //
          "                                                      \r\n" + //
          " e88~-_       e           e    e      ,d88~~\\         \r\n" + //
          "d888   \\     d8b         d8b  d8b     8888            \r\n" + //
          "8888        /Y88b       d888bdY88b    `Y88b           \r\n" + //
          "8888       /  Y88b     / Y88Y Y888b    `Y88b,         \r\n" + //
          "Y888   /  /____Y88b   /   YY   Y888b     8888         \r\n" + //
          " \"88_-~  /      Y88b /          Y888b \\__88P'         \r\n" + //
          "                                                      \r\n" + //
          "";
   

  
    /**
     * This static final string helps reset the printed text to default colour 
     */
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * This static final string helps to print text in REN 
     */
    public static final String ANSI_RED = "\u001B[31m";

    /**
     * This static final string helps to print text in GREEN 
     */
    public static final String ANSI_GREEN = "\u001B[32m";

    /**
     * This static final string helps to print text in YELLOW 
     */
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * This static final string helps to print text in BLUE 
     */
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

    /**
     * This method is static and it breaks down the string to the bits of strings in the appropriate size, 
     * the bits of strings then is added to the return ArrayList in order 
     * 
     * @param string the initial string to be broken down 
     * @param maxlength the max length the bits of strings can take 
     * @return the ArrayList containing the bits of strings in order
     */
    public static ArrayList<String> breakDownString(String string, int maxlength){
      ArrayList<String> returnString = new ArrayList<String>(); 
      
      int i=0;
      int lastWhiteSpace = 0; 
      int startOfSubString = 0; 
      int currentStringLength =1;
      int initialStart = 0; 

      while (i<string.length()){
        if (string.charAt(i) == ' ') lastWhiteSpace = i; 

        if (currentStringLength==maxlength){
          if (initialStart == lastWhiteSpace){
            returnString.add(string.substring(startOfSubString, initialStart+maxlength));
            currentStringLength = 1; 
            startOfSubString = initialStart+maxlength + 1;
            initialStart = maxlength + 1; 
            i = startOfSubString;
          }
          else{
            returnString.add(string.substring(startOfSubString, lastWhiteSpace));
            currentStringLength = 1; 
            startOfSubString = lastWhiteSpace + 1;
            i = startOfSubString;
          }
        }
        else{
          currentStringLength++;
          i++;
        }
      }

      returnString.add(string.substring(startOfSubString, string.length()));

      return returnString;
    }
}
