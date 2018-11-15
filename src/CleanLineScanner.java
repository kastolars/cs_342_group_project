/*
Author: Karol Stolarski
netID: kstola2

Clean line scanner allows me to take a line of a file
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
}
