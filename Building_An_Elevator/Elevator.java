/*
 * Name:
 *  Elevator.java
 *
 * Version:
 *  1.0
 *
 * Revisions:
 *  None
 */
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Represents an Elevator that runs, collects Persons, and drops them off at
 * floors.
 *
 * @author Anuradha Bhave
 * @author Himanshi Chetwani
 */
public class Elevator extends Thread {

    final static int NUMBER_OF_FLOORS = 5;
    final private int ELEVATOR_CAPACITY = 700;
    private int currentElevatorCapacity;
    private int currentElevatorFrequency;
    private int currentPosition;
    private ArrayList<Person> peopleInLift = new ArrayList<>();
    private ExecutorService elevatorExecutor =
            Executors.newFixedThreadPool(10);
    private ArrayList<Queue<Person>> floorsQueue = new ArrayList<>();

    /**
     * Default constructor
     */
    Elevator() {
        for (int i = 1; i <= NUMBER_OF_FLOORS; i++) {
            Queue<Person> queue = new Queue<>();
            floorsQueue.add(queue);
        }
    }

    /**
     * Starts the elevator, and spawns off person threads
     * @throws InterruptedException could be thrown by join()
     */
    void startElevator() throws InterruptedException {
        for (int i = 0; i < peopleInLift.size(); i++) {
            Person p = peopleInLift.get(i);
            elevatorExecutor.execute(p);
            p.join();
        }
        sort();
        while (peopleInLift.size()!=0){
            goUp();
            System.out.println("Starting to go down");
            goDown();

        }
    }

    /**
     * Checks if elevator weight has reached its maximum value
     * @return true if the elevator is full
     */
    private boolean isElevatorFull() {
        return (currentElevatorCapacity > ELEVATOR_CAPACITY);
    }

    /**
     * For the elevator to go up
     */
    private void goUp() {
        for (int i = 1; i <= NUMBER_OF_FLOORS; i++) {
            currentPosition = i;
            System.out.println("Elevator is on " + i);

            for (int j = 0; j < peopleInLift.size(); j++) {
                Person p = peopleInLift.get(j);
                if (p.destinationFloor == currentPosition) {
                    System.out.println(p.getPersonName()
                            + " got out on floor: " + currentPosition);
                    peopleInLift.remove(j);
                    j--;
                    floorsQueue.get(currentPosition - 1).add(p);

                }
            }
        }
    }

    /**
     * For the elevator to go down
     */
    private void goDown() {
        for (int i = NUMBER_OF_FLOORS; i > 0; i--) {
            System.out.println("Elevator is at " + i);
            currentPosition = i;

            for (int j = 0; j < peopleInLift.size(); j++) {
                Person p = peopleInLift.get(j);
                if (p.destinationFloor == currentPosition) {
                    System.out.println(p.getPersonName() + " got out of the " +
                            "elevator");
                    peopleInLift.remove(j);
                    j--;

                    floorsQueue.get(currentPosition - 1).add(p);

                }
            }

            System.out.println("Checking if any people are waiting on floor "
                    + currentPosition);
            Queue<Person> currentFloorQueue =
                    floorsQueue.get(currentPosition - 1);
            if (currentFloorQueue.size() != 0) {
                int floorQueueSize = currentFloorQueue.size();
                while (currentFloorQueue.size() != 0) {
                    Person p = currentFloorQueue.remove();
                    if (currentPosition != 1) {
                        peopleInLift.add(p);

                        System.out.println(p.getPersonName() +
                                " got added on the" + " way down");
                        p.destinationFloor = 1;
                    }
                }
            } else {
                System.out.println("Floor was empty");
            }

        }
    }

    /**
     * Adds to weight of elevator every time a person enters or exits
     * @param weightOfPerson weight of person
     * @param entry if the person has entered the elevator
     * @return true if elevator weight is modified
     */

    private boolean modifyElevatorCapacity(int weightOfPerson, boolean entry) {
        if (entry) {
            currentElevatorCapacity += weightOfPerson;
            if (!isElevatorFull()) {
                currentElevatorFrequency++;
                return true;
            } else {
                currentElevatorCapacity -= weightOfPerson;
                return false;
            }
        } else {
            currentElevatorCapacity -= weightOfPerson;
            return true;
        }

    }

    /**
     * Sort people on the basis of their destination floors
     */
    private void sort() {
        Person tempOne, tempTwo;
        for (int left = 0; left < peopleInLift.size() - 1; left++) {
            for (int right = 0; right < peopleInLift.size() - 1; right++) {
                tempOne = peopleInLift.get(right);
                tempTwo = peopleInLift.get(right + 1);
                if (tempOne.destinationFloor > tempTwo.destinationFloor) {
                    peopleInLift.set(right + 1, tempOne);
                    peopleInLift.set(right, tempTwo);
                }

            }
        }
    }

    /**
     * The main method
     * @param args None
     * @throws InterruptedException could be thrown by executor shutdown()
     */
    public static void main(String[] args) throws InterruptedException {
        Random r = new Random();
        Elevator machine = new Elevator();
        for (int i = 1; i < 11; i++) {
            int randomWeight = (r.nextInt((250 - 100) + 1) + 100);
            String name = "Person-" + i;
            Person p = new Person(randomWeight, name, 1,
                    true);
            if (machine.modifyElevatorCapacity(randomWeight, p.entry)) {
                machine.peopleInLift.add(p);
            }
        }
        System.out.println("Number of people in the elevator: "
                + machine.currentElevatorFrequency);
        System.out.println("Weight in elevator: " +
                machine.currentElevatorCapacity);
        machine.startElevator();
        machine.elevatorExecutor.shutdown();
    }

}