package autopool;

import java.util.*;

public class LockerRoom {

    public static class Locker {

        private final int number;
        private boolean free;

        private Locker(int number) {
            this.number = number;
            this.free = true;
        }

        public int getNumber() {
            return number;
        }

        public boolean isFree() {
            return free;
        }

        public void take() {
            free = false;
        }

        public void free() {
            free = true;
        }

        @Override
        public String toString() {
            return "Locker #" + number + " is " + (free ? "free" : "occupied");
        }
    }

    public static final int LOCKER_COUNT = 50;
    public static final int INITIAL_MINIMAL_LOCKER_DISTANCE = 5;

    private int currentMinimalLockerDistance;
    private final List<Locker> lockers;
    private final Random random;

    public LockerRoom() {
        lockers = new ArrayList<>();
        random = new Random(System.currentTimeMillis());
        currentMinimalLockerDistance = INITIAL_MINIMAL_LOCKER_DISTANCE;
        for (int i = 0; i < LOCKER_COUNT; i++) {
            var locker = new Locker(i + 1);
            if (random.nextBoolean()) {
                locker.take();
            }
            lockers.add(locker);
        }
    }

    public Locker requestLocker() {

        var freeLockers = lockers.stream().filter(locker -> locker.free).toList();

        if (freeLockers.isEmpty())
            return null;

        List<Locker> fittingLockers = new ArrayList<>();
        do {
            tryToFindFittingLocker(freeLockers, fittingLockers);
        } while (fittingLockers.isEmpty());


        int index = random.nextInt(fittingLockers.size());
        return fittingLockers.get(index);
    }

    public void log() {
        for (var locker : lockers) {
            System.out.println(locker);
        }
    }

    private void tryToFindFittingLocker(List<Locker> freeLockers, List<Locker> fittingLockers) {
        var distance = currentMinimalLockerDistance;
        boolean occurredAtLeastOnce = false;
        for (var freeLocker : freeLockers) {
            int n = freeLocker.number - 1;
            boolean fit = true;
            for (int i = -distance; i <= +distance; i++) {
                if (i == 0 || n + i < 0 || n + i >= lockers.size()) continue;
                if (!lockers.get(n + i).isFree()) {
                    fit = false;
                    break;
                }
            }
            if (fit) {
                fittingLockers.add(lockers.get(n));
                occurredAtLeastOnce = true;
            }
        }
        if (!occurredAtLeastOnce) {
            currentMinimalLockerDistance--;
        }
    }
}
