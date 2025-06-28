package dto;

public class Theater {
    private int theaterId;
    private String name;
    private String location;

    public Theater(int theaterId, String name, String location) {
        this.theaterId = theaterId;
        this.name = name;
        this.location = location;
    }

    public int getTheaterId() { return theaterId; }
    public String getName() { return name; }
    public String getLocation() { return location; }
}

