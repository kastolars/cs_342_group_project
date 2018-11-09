import java.util.ArrayList;
import java.util.HashMap;

public class Place {
    private int id;
    private String name;
    private String description;
    private static HashMap<Integer, Place> places = new HashMap<Integer, Place>();
    private ArrayList<Direction> directions = new ArrayList<Direction>();

    public Place(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        places.put(id, this);
    }

    public static Place getPlaceById(int id){
        return places.get(id);
    }

    public void addDirection(Direction d){
        directions.add(d);
    }

    public Place followDirection(String s){
        for (Direction d : directions){
            if (d.match(s)){
                try {
                    return d.follow(s);
                } catch (Direction.LockedDirectionException e){
                    return this;
                }
            }
        }
        return this;
    }

    public boolean isExit(){
        return id == 1;
    }
}
