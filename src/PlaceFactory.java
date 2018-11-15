import java.util.Scanner;

public class PlaceFactory {

    public static void makePlaces(Scanner sc, int numPlaces) {

        // For loop for each place
        for (int i = 0; i < numPlaces; i++) {

            // Get ID and Name
            String line = CleanLineScanner.getCleanLine(sc);
            String[] arr = line.split("\\s+");
            int ID = CleanLineScanner.toInt(arr[0]);
            String name = arr[1].trim();

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
            } else {
                Place p = new Place(ID, name, description);
            } // else
        } // outer for loop

        Place nowhere = new Place(0, "Nowhere", "There's an abundance of nothing here.");
        Place exit = new Place(1,"Exit", "This is an exit!");
    }
}
