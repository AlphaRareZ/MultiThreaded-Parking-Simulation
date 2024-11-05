public class Car extends Thread {


    private Gate gate;

    private int id;
    private int arrivalTime;
    private int stayTime;

    public Car(int id, int arrivalTime, int stayTime, Gate gate) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.stayTime = stayTime;
        this.gate = gate;
    }

    public Gate getGate() {
        return gate;
    }

    public int getCarId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getStayTime() {
        return stayTime;
    }

    @Override
    public void run() {
        // arrival
        int occupiedSpots = gate.parkingLot.acquire(this);
        // parking
        String parkingString = String.format("Car %s from Gate %s parked. (Parking Status: %s spots occupied)", id, gate.getGateId(), occupiedSpots);
        System.out.println(parkingString);
        // waiting
        try {
            Thread.sleep(this.stayTime * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // leaving
        occupiedSpots = gate.parkingLot.release(this);
        String leavingString = String.format("Car %s from Gate %s left after %s units of time. (Parking Status: %s spots occupied)", id, gate.getGateId(),stayTime, occupiedSpots);
        System.out.println(leavingString);
    }
}