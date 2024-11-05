import java.sql.Time;

public class TimeIncrementer extends Thread {
    ParkingLot parkingLot = null;

    public TimeIncrementer(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                parkingLot.incrementTime();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
