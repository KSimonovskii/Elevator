package telran.elevator.task;

import telran.elevator.model.Elevator;

public class Track implements Runnable{

    private int nRaces;
    private int capacity;
    private Elevator elevator;

    public Track(int nRaces, int capacity, Elevator elevator) {
        this.nRaces = nRaces;
        this.capacity = capacity;
        this.elevator = elevator;
    }

    @Override
    public void run() {

        for (int i = 0; i < nRaces; i++) {
            elevator.add(capacity);
        }

    }
}
