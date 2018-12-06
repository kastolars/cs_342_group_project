/* Name: Ayush Patel, Luke Paltzer, Karol Stolarski
 * Group: 34
 * Homework 4: Group Project
 * Description: Factory method to read and initialize all the places present
 *              in GDF file. This is used to implement different types of rooms
 */

import java.util.Scanner;

public class PlaceFactory {

    public static void makePlaces(Scanner sc, int numPlaces) {

        // For loop for each place
        for (int i = 0; i < numPlaces; i++) {

            // Get ID and Name
            int ID = Integer.parseInt( sc.next() );
            // String line = CleanLineScanner.getCleanLine(sc);
            String line = sc.nextLine().trim();
            String name = line;
            // System.out.println(name);

            // System.out.println(name);
            // Get number of description lines
            line = CleanLineScanner.getCleanLine(sc);
            int count = CleanLineScanner.extractInt(line);

            // Complete description
            String description = "";
            for (int j = 0; j < count; j++){
                description += CleanLineScanner.getCleanLine(sc) + "\n";
            } // inner for loop

            // Call the Place constructor
            if (ID < 0) {
                DarkPlace dp = new DarkPlace(ID, name, description);
                // dp.display();
            } else {
                Place p = new Place(ID, name, description);
            } // else
        } // outer for loop

        Place nowhere = new Place(0, "Nowhere", "There's an abundance of nothing here.");
        Place exit = new Place(1,"Exit", "This is an exit!");
    }
}
