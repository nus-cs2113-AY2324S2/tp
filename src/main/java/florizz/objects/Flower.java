package florizz.objects;

public class Flower {
    private String name;
    private String occasion;
    private String colour;

    public Flower(String name, String colour, String occasion) {
        this.name = name;
        this.occasion = occasion;
        this.colour = colour;
    }

    public String getFlowerName() {
        return name;
    }

    @Override
    public String toString() {
        return ("Name: " + name + "\n" + "Colour: " + colour + "\n" + "Occasion: " + occasion + "\n");
    }
}
