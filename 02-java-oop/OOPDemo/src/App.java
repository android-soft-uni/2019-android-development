import softuni_demo.Circle;
import softuni_demo.shelter.Shelter;

public class App {


    public static void main(String[] args) {
        Shelter shelter = new Shelter();
        shelter.addDog("Pesho");
        shelter.addDog("Pesho2");
        shelter.killDog("Pesho2");
    }
}
