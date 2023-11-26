package scs3grp5.ui.ulti;

import java.io.IOException;

/**
 * This class is responsible for changing the console output screen to a blank screen.
 * This is done using the appropriate command depending on the operating system.
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class ChangePage {

    /**
     * Changes the console output screen to a new blank screen.
     * This is done using the appropriate command depending on the operating system.
     */
    public static void changePage() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-like systems
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
