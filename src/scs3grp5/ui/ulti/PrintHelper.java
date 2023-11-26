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
          "                                                                                                          \r\n" + //
          "                                                                                                          \r\n" + //
          "       __.....__                    .--.         .--.   _..._                                             \r\n" + //
          "   .-''         '.                  |__|         |__| .'     '.   .--./)                                  \r\n" + //
          "  /     .-''\"'-.  `.                .--.     .|  .--..   .-.   . /.''\\\\                                   \r\n" + //
          " /     /________\\   \\ ____     _____|  |   .' |_ |  ||  '   '  || |  | |                                  \r\n" + //
          " |                  |`.   \\  .'    /|  | .'     ||  ||  |   |  | \\`-' /                                   \r\n" + //
          " \\    .-------------'  `.  `'    .' |  |'--.  .-'|  ||  |   |  | /(\"'`                                    \r\n" + //
          "  \\    '-.____...---.    '.    .'   |  |   |  |  |  ||  |   |  | \\ '---.                                  \r\n" + //
          "   `.             .'     .'     `.  |__|   |  |  |__||  |   |  |  /'\"\"'.\\                                 \r\n" + //
          "     `''-...... -'     .'  .'`.   `.       |  '.'    |  |   |  | ||     ||                                \r\n" + //
          "                     .'   /    `.   `.     |   /     |  |   |  | \\'. __//                                 \r\n" + //
          "                    '----'       '----'    `'-'      '--'   '--'  `'---'                                  \r\n" + //
          "            .-'''-.        .-'''-.                                                                        \r\n" + //
          "           '   _    \\     '   _    \\ _______                                                              \r\n" + //
          "         /   /` '.   \\  /   /` '.   \\\\  ___ `'.                  /|                        __.....__      \r\n" + //
          "  .--./).   |     \\  ' .   |     \\  ' ' |--.\\  \\                 ||    .-.          .- .-''         '.    \r\n" + //
          " /.''\\\\ |   '      |  '|   '      |  '| |    \\  '                ||     \\ \\        / //     .-''\"'-.  `.  \r\n" + //
          "| |  | |\\    \\     / / \\    \\     / / | |     |  ' ,.----------. ||  __  \\ \\      / //     /________\\   \\ \r\n" + //
          " \\`-' /  `.   ` ..' /   `.   ` ..' /  | |     |  |//            \\||/'__ '.\\ \\    / / |                  | \r\n" + //
          " /(\"'`      '-...-'`       '-...-'`   | |     ' .'\\\\            /|:/`  '. '\\ \\  / /  \\    .-------------' \r\n" + //
          " \\ '---.                              | |___.' /'  `'----------' ||     | | \\ `  /    \\    '-.____...---. \r\n" + //
          "  /'\"\"'.\\                            /_______.'/                 ||\\    / '  \\  /      `.             .'  \r\n" + //
          " ||     ||                           \\_______|/                  |/\\'..' /   / /         `''-...... -'    \r\n" + //
          " \\'. __//                                                        '  `'-'`|`-' /                           \r\n" + //
          "  `'---'                                                                  '..'                            \r\n" + //
          "";
   

  
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

      while (i<string.length()){
        if (string.charAt(i) == ' ') lastWhiteSpace = i; 

        if (currentStringLength==maxlength){
          returnString.add(string.substring(startOfSubString, lastWhiteSpace));
          currentStringLength = 1; 
          startOfSubString = lastWhiteSpace + 1;
          i = startOfSubString;
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
