package dto;

public class Screen {
    private int screenId;
    private int theaterId;
    private int screenNumber;
    private int capacity;

    public Screen(int screenId, int theaterId, int screenNumber, int capacity) {
        this.screenId = screenId;
        this.theaterId = theaterId;
        this.screenNumber = screenNumber;
        this.capacity = capacity;
    }

    public int getScreenId() { return screenId; }
    public int getTheaterId() { return theaterId; }
    public int getScreenNumber() { return screenNumber; }
    public int getCapacity() { return capacity; }
}
