import java.lang.reflect.AnnotatedArrayType;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class Gate extends Thread {
    ParkingLot parkingLot = null;
    private List<Car> carsWaiting = new ArrayList<>();
    private int gateId;

    public Gate(int id, ParkingLot parkingLot) {
        gateId = id;
        this.parkingLot = parkingLot;
    }

    public List<Car> getCarsWaiting() {
        return carsWaiting;
    }

    public int getGateId() {
        return gateId;
    }


    @Override
    public void run() {
        boolean[] connected = new boolean[carsWaiting.size()];
        HashMap<Car, Boolean> map = new HashMap<>();
        while (map.size() != connected.length) {
            for (int i = 0; i < carsWaiting.size(); i++) {
                int currTime = parkingLot.time;
//                System.out.println(currTime);
                var currCar = carsWaiting.get(i);
                if (!connected[i]) {
//                    System.out.println(currCar.getCarId() + " Not Connected Time is " + i);
                    if (currCar.getArrivalTime() == currTime) {
                        connected[i] = true;
                        currCar.start();
                    }
                }
                if (connected[i]) {
                    map.put(currCar, true);
                }
            }
        }
    }
}