package florizz.objects;

import java.util.ArrayList;

public class Flower {
    private String name = "";
    private ArrayList<Occasion> occasions = null;
    private Colour colour = null;
    private String price = "";

    public enum Colour {
        WHITE, BLUE, RED, PINK
    }

    public enum Occasion {
        FUNERAL, WEDDING, VALENTINES, MOTHERS_DAY
    }

    public Flower() {
        this.name = "";
    }

    public Flower(String name, Colour colour, ArrayList<Occasion> occasion, String price) {
        this.name = name;
        this.occasions = occasion;
        this.colour = colour;
        this.price = price;
    }
    public Flower(String name, Colour colour, Occasion occasion, String price) {
        this.name = name;
        this.occasions = new ArrayList<Occasion>();
        occasions.add(occasion);
        this.colour = colour;
        this.price = price;
    }

    public static Occasion stringToOccasion(String ocassionString){
        return Occasion.valueOf(ocassionString.replaceAll(" ", "_").toUpperCase());
    }

    public static String occasionToString(Occasion occasion){
        return occasion.toString().charAt(0) +
                occasion.toString().replaceAll("_", " ").toLowerCase().substring(1);
    }

    public static Colour stringToColour(String colourString){
        return Colour.valueOf(colourString.replaceAll(" ", "_").toUpperCase());
    }
    public String getFlowerName() {
        return name;
    }

    public String getNameAndColour() {
        return colour.toString().charAt(0) + colour.toString().substring(1).toLowerCase() + " " + name;
    }

    public String getColour (){
        return colour.toString();
    }
    public ArrayList<Occasion> getOccasion() {
        return occasions;
    }

    @Override
    public String toString() {
        return ("Name: " + name + "\n" + "Colours: " + colour.toString() + "\n" + "Occasions: " + occasions.toString()
            + "\n" + "Price: " + price);
    }
}
