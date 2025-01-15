package telran.elevator;

import telran.elevator.model.Elevator;
import telran.elevator.task.Track;

public class ElevatorAppl {

    private static final int N_TRUCK = 10_000;
    private static final int N_RACES = 10;
    private static final int CAPACITY = 20;

    public static void main(String[] args) throws InterruptedException {

        Elevator elevator = new Elevator("V.I. Lenin");
        Thread[] threads = new Thread[N_TRUCK];

        for (int i = 0; i < N_TRUCK; i++) {
            threads[i] = new Thread(new Track(N_RACES, CAPACITY, elevator));
            threads[i].start();
        }

        for (int i = 0; i < N_TRUCK; i++) {
            threads[i].join();
        }

        System.out.println("Elevator " + elevator.getName() + " has " + elevator.getCurrentVolume());

    }
}
