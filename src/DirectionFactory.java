import java.util.Scanner;

public class DirectionFactory {



    public static void makeDirections(Scanner sc, int numDirs){

        for (int i = 0; i < numDirs; i++){
            // Get the line and split it
            String line = CleanLineScanner.getCleanLine(sc);
            String[] arr = line.split("\\s+");

            // Get the ID's
            int ID = CleanLineScanner.toInt(arr[0]);
            int sourceID = CleanLineScanner.toInt(arr[1]);
            String type = arr[2];
            int destinationID = CleanLineScanner.toInt(arr[3]);
            int lockPattern = CleanLineScanner.toInt(arr[4]);

            // Create the direction
            if (ID < 0) {
                BoobyTrappedDirection btd = new BoobyTrappedDirection(ID, sourceID, type, destinationID, lockPattern);
            } else if (lockPattern < 0) {
                Portal p = new Portal(ID, sourceID, type, destinationID, lockPattern);
            } else {
                Direction d = new Direction(ID, sourceID, type, destinationID, lockPattern);
            }
        }
    }
}
