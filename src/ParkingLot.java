public class ParkingLot {
    // Semaphore Class
    public int time = 0;
    int capacity;
    int occupied = 0;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void incrementTime() {
        time++;
    }

    // this function puts the car in an empty slot and returns the number of occupied spots
    public synchronized int acquire(Car car) {
        capacity--;
        occupied++;
        String arrivalString = String.format("Car %s from Gate %s arrived at time %s", car.getCarId(), car.getGate().getGateId(), time);
        if (capacity < 0) {
            String waitingString = String.format("Car %s from Gate %s waiting for a spot", car.getCarId(), car.getGate().getGateId());
            System.out.println(arrivalString);
            System.out.println(waitingString);
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            notifyAll();
        } else {
            System.out.println(arrivalString);
        }
        return occupied;
    }

    public synchronized int release(Car car){
        capacity++;
        occupied--;
        if (capacity <= 0) {
            // انقل دي في ال Gate
//            String s = String.format("Car %s from Gate %s left after %s units of time %s", car.getCarId(), car.getGate().getGateId(), car.getStayTime());
            notifyAll();
        }
        return occupied;
    }
}