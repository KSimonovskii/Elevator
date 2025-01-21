package telran.elevator;

import telran.elevator.model.Elevator;
import telran.elevator.task.Track;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ElevatorAppl {

    private static final int N_TRUCK = 10_000;
    private static final int N_RACES = 10;
    private static final int CAPACITY = 20;

    public static void main(String[] args) throws InterruptedException {

        Elevator elevator1 = new Elevator("V.I. Lenin");
        Elevator elevator2 = new Elevator("Komsomolskiy");

        Elevator[] elevators = {elevator1, elevator2};

        Thread[] threads = new Thread[N_TRUCK * elevators.length];

        LocalDateTime start = LocalDateTime.now();
        for (int i = 0; i < N_TRUCK; i++) {

            int balance = CAPACITY;
            for (int j = 0; j < elevators.length; j++) {
                int weight = j == elevators.length - 1? balance : CAPACITY / elevators.length;
                threads[i] = new Thread(new Track(N_RACES, weight, elevators[j]));
                threads[i].start();
                balance -= weight;
            }
        }

        for (int i = 0; i < N_TRUCK; i++) {
            threads[i].join();
        }

        long duration = ChronoUnit.MILLIS.between(start, LocalDateTime.now());
        System.out.println("Duration of operation: " + duration + " millisec.");
        for (Elevator elevator: elevators) {
            System.out.println("Elevator " + elevator.getName() + " has " + elevator.getCurrentVolume());
        }

    }
}
