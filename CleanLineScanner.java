/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * Description: Clean line scanner allows me to take a line of a file
				and strip away all comments and leading/trailing whitespace.
 */

import java.util.Scanner;

public class CleanLineScanner {

    public static int toInt(String s){
        return Integer.valueOf(s);
    }

    public static String getCleanLine(Scanner sc){
        String line = null;
        while (sc.hasNext()){
            line = sc.nextLine();
            if (line.contains("//")) {
                line = line.substring(0, line.indexOf("//"));
            }
            line = line.trim();
            if (line.length() > 0){
                break;
            }
        }
        return line;
    }

    // Oftentimes just the integer is needed so this extracts it from a string
    public static int extractInt(String line){
        return Integer.valueOf(line.replaceAll("\\D*", ""));
    }

    /* Takes the scanner from any of the class and reads the line and returns
	 * the clean line that is removes alls comments, double spaces, tabs,
	 * and trailing whitespaces. if the line is empty or it only has comments
	 * it returns "0" indicating there is not thing to be read in that line
	 * Can be called from any class and maintains the scanner position throughoutc
	 */
	public static String getLine(Scanner scan) {
		String next = scan.nextLine();
		
		next = next.replaceAll("\\s+", " ");	//remove all double spaces
		int index = next.indexOf("//");			//locate the comments
		
		//Checks if the line is empty and if the line has only comments
		//returns "0" to indicate nothing important in the line
		if(next.length() == 0) {
			return "\0";
		} else if(index == 0) {
			return "\0";
		}

		//Extra check to see if there is comment in the line or not.
		//negative value means no comment and just reads the length of the line
		if(index < 0) {
			next = next.substring(0, next.length());
		} else {
			next = next.substring(0, index-1);		//extract substring without the comment
		}
		next.trim();							//replaces all the trailing whitespaces
		
		return next;
	}
	
	
	/* Takes the scanner passed by Place() and reads the number of lines in
	 * description and loops and for that many times and gets all the description
	 * lines and returns the string with all the description lines from the file
	 * correctly formated (new line). The function can be used by Artifact class
	 * since the the Artifacts follow the same format for description as Places.
	 */
	public static String getDescription(Scanner scan) {
		String desc = scan.nextLine();			//# of lines of description
		int num = Integer.parseInt(desc);			//change to int
		String send = null;
		
		for(int i = 0; i < num; i++) {
			desc = scan.nextLine();
			if(i == 0) {
				send = desc;
			} else {
				send += desc;
			}
			send += "\n";
		}
		
		return send;
	}
}
