

public class TextInterface implements UserInterface {


    public TextInterface () {

    }

    //@Override
    public void display (String s) {

        System.out.println(s);

    }

    //@Override
    public String getLine() {

        String s =  KeyboardScanner.getKeyboardScanner().nextLine().trim();
        //System.out.println("Scanned in: " + s);
        return s;
        
    }

    @Override
    public void frameUpdate(Boolean b) {
        //System.out.println("I am Here");
    }



}