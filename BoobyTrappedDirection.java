public class BoobyTrappedDirection extends Direction {

    private boolean active;

    public BoobyTrappedDirection(int id, int sourceId, String type, int destId, int lockPattern) {
        super(id, sourceId, type, destId, lockPattern);
        // System.out.println( "Glados" );
        active = true;
    }

    public void deactivate(Artifact a){
        if (a.ID() == 31){
            active = false;
        }
    }

    @Override
    public Place follow(String s) throws LockedDirectionException {
        if (active){
            trigger();
        }
        return super.follow(s);
    }

        public void trigger(){
        //c.takeDamage(10);
    }
}
