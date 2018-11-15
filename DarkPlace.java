public class DarkPlace extends Place{

    private final String darkName;
    private final String darkDescription;
    private boolean dark;

    public DarkPlace(int id, String name, String description) {
        super(id, name, description);
        darkName = "Dark Place";
        darkDescription = "You can't see a thing in here!";
        dark = true;
    }

    public void illuminate(Artifact a){
        if (a.ID() == 30){
            dark = false;
        }
    }

    @Override
    public void display() {
        if (!dark){
            super.display();
        } else {
            System.out.println(darkName);
            System.out.println(darkDescription);
        }
    }
}
