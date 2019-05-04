package softuni_demo.shelter;

public class Dog {

    private String name;
    private long timestampRegistered;

    protected Dog(String name) {
        this.name = name;
        this.timestampRegistered = System.currentTimeMillis();
    }

    public String getName(){
        return name;
    }
}
