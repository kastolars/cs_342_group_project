import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.abs;

public class Place {
    private int id;
    private String name;
    private String description;
    private static Place start;
    private static HashMap<Integer, Place> places = new HashMap<Integer, Place>();
    private ArrayList<Direction> directions = new ArrayList<Direction>();

    public Place(int id, String name, String description) {
        this.id = abs(id);
        this.name = name;
        this.description = description;
        if (places.isEmpty()) {
            start = this;
        }
        places.put(id, this);
    }

    public static Place getStart(){
        return start;
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

    public static int getNumPlaces(){
        return places.size();
    }

    public boolean isExit(){
        return id == 1;
    }

    public void display(){
        System.out.println(name);
        System.out.println(description);
    }
}
