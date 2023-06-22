import java.time.LocalDateTime;

public class CurrentTime {

    private int seconds;

    public void timeInSeconds() {
        LocalDateTime now = LocalDateTime.now();
        seconds = now.getSecond();
        setSeconds(seconds);
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }
}
