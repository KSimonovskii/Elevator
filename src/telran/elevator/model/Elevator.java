package telran.elevator.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Elevator {

    ReadWriteLock rwLock = new ReentrantReadWriteLock();
    Lock rLock = rwLock.readLock();
    Lock wLock = rwLock.writeLock();
    private String name;
    private int currentVolume;

    public Elevator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCurrentVolume() {

        rLock.lock();
        try {
            return currentVolume;
        } finally {
            rLock.unlock();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(int portion) {
        wLock.lock();
        try {
            currentVolume = currentVolume + portion;
        } finally {
            wLock.unlock();
        }

    }
}
