/*	Ayush Patel
 *  apate324
 *  Homework #3: Addition of Characters and Inheritance
 *  Description: KeyboardScanner class: This is a Singleton class that will return a Scanner
 *  			 to read from the keyboard. If a Scanner already exist for this it will call return that
 *  			 if not it will create a new scanner and return it.
 */


import java.util.Scanner;

public class KeyboardScanner {
	
	private static KeyboardScanner keyboard = null;
	public static Scanner keyScan;
	
	private KeyboardScanner() {
		keyScan = new Scanner(System.in);
	}
	
	public static Scanner getKeyScanner() {
		
		if(keyboard == null){
			keyboard = new KeyboardScanner();
		}
		
		return keyScan;
		
	}

}
