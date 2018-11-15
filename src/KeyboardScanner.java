import java.util.Scanner;

/**
 * AUTHOR: lpaltz2 -- hw3
 * DATE: October 25, 2018
 * 
 * DESCRIPTION:
 *  This class garuentees that there is only one keyboard scanner in use
 *  uses the singleton design pattern
 *  
 *  Only make one scanner -- dont be wasteful
 */

public class KeyboardScanner {
    private static KeyboardScanner INSTANCE = null;
    private static final Scanner scanner = new Scanner( System.in );

    private KeyboardScanner(){ }

    public static Scanner getKeyboardScanner(){
        if ( INSTANCE == null )
            INSTANCE = new KeyboardScanner();
        
        return scanner;
    }
}