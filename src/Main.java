import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // don't forget to make thread for time incrementer and run it before the program
        Scanner scanner = new Scanner(System.in);

        // initialize the parking lot
        System.out.println("Enter Available Parking Spots");
        ParkingLot parkingLot = new ParkingLot(scanner.nextInt());

        // initialize the 3 Gates
        Gate gate1 = new Gate(1, parkingLot);
        Gate gate2 = new Gate(2, parkingLot);
        Gate gate3 = new Gate(3, parkingLot);


        // initialize the time incrementer
        TimeIncrementer timeIncrementer = new TimeIncrementer(parkingLot);

        // Enter total number of cars
        System.out.println("Enter Total Number of Cars ");
        int n = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Gate Number, Car Number, Arrive Time, Leaving Time Respectively");


        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            // "Gate 1, Car 0, Arrive 0, Parks 3"
            String[] arr = s.split(",");
            // "Gate 1", "Car 0", "Arrive 0", "Parks 3"
            List<Integer> data = new ArrayList<>();
            for (int j = 0; j < arr.length; j++) {
                arr[j] = arr[j].trim();
                var x = arr[j].split(" ");
                // "Gate","1"
                // "Car","0"
                // "Arrive","0"
                // "Parks","3"
                data.add(Integer.parseInt(x[1]));
            }

            if (data.get(0) == 1) {
                gate1.getCarsWaiting().add(new Car(data.get(1), data.get(2), data.get(3), gate1));
            } else if (data.get(0) == 2) {
                gate2.getCarsWaiting().add(new Car(data.get(1), data.get(2), data.get(3), gate2));
            } else if (data.get(0) == 3) {
                gate3.getCarsWaiting().add(new Car(data.get(1), data.get(2), data.get(3), gate3));
            }
        }
        timeIncrementer.start();
        gate1.start();
        gate2.start();
        gate3.start();

        try {
            gate1.join();
            gate2.join();
            gate3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Now, stop timeIncrementer
        timeIncrementer.interrupt();
    }
}
/*
*
Gate 1, Car 0, Arrive 0, Parks 3
Gate 1, Car 1, Arrive 1, Parks 4
Gate 1, Car 2, Arrive 2, Parks 2
Gate 1, Car 3, Arrive 3, Parks 5
Gate 1, Car 4, Arrive 4, Parks 3
Gate 2, Car 5, Arrive 3, Parks 4
Gate 2, Car 6, Arrive 6, Parks 3
Gate 2, Car 7, Arrive 7, Parks 2
Gate 2, Car 8, Arrive 8, Parks 5
Gate 2, Car 9, Arrive 9, Parks 3
Gate 3, Car 10, Arrive 2, Parks 4
Gate 3, Car 11, Arrive 5, Parks 3
Gate 3, Car 12, Arrive 7, Parks 2
Gate 3, Car 13, Arrive 10, Parks 5
Gate 3, Car 14, Arrive 11, Parks 3
*/