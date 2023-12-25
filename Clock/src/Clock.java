import java.util.Random;
class Clock {
    private int hour;

    public Clock() {
        Random rand = new Random();
        this.hour = rand.nextInt(12) + 1;
    }

    public int getHour() {
        return hour;
    }

    public void increaseHour() {
        hour = (hour % 12) + 1;
    }
}
