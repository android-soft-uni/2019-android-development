package softuni_demo.shelter;

import java.util.ArrayList;
import java.util.List;

public class Shelter {

    private List<Dog> allDogs;

    public Shelter() {
        this.allDogs = new ArrayList<>();
    }

    public void addShepardDog(String name) {
//        allDogs.add(new GermanShepardDog(name));
    }

    public void addPincher(String name) {
        allDogs.add(new PincherDog(name));
    }

    public void killDog(String name) {
        Dog dogToBeKilled = null;
        for (Dog dog : allDogs) {
            if(dog.getName().equals(name)) {
                dogToBeKilled = dog;
                break;
            }
        }
        if(dogToBeKilled != null) allDogs.remove(dogToBeKilled);
    }
}
