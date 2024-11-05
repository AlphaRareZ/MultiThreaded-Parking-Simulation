import java.sql.Time;

public class TimeIncrementer extends Thread {
    ParkingLot parkingLot = null;

    public TimeIncrementer(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Your increment logic
                Thread.sleep(1000);
                parkingLot.incrementTime();// Example sleep
            }
        } catch (InterruptedException e) {
            // Handle interruption and exit
            Thread.currentThread().interrupt(); // Preserve the interrupted status
        }
    }

}
