package telran.elevator.task;

import telran.elevator.model.Elevator;

public class Track implements Runnable{

    private static Object mutex = new Object();
    private int nRaces;
    private int capacity;
    private Elevator[] elevators;

    public Track(int nRaces, int capacity, Elevator[] elevators) {
        this.nRaces = nRaces;
        this.capacity = capacity;
        this.elevators = elevators;
    }

    @Override
    public void run() {

        for (int i = 0; i < nRaces; i++) {

            int balance = capacity;
            for (int j = 0; j < elevators.length; j++) {
                int weight = j == elevators.length - 1? balance : capacity / elevators.length;
                synchronized (elevators[j]) {
                    elevators[j].add(weight);
                }
                balance -= weight;
            }
        }
    }
}
