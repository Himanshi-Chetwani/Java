/*
 * Name:
 *  Elevator.java
 *
 * Version:
 *  1.1
 *
 * Revisions:
 *  Addition of peopleAndSummary()
 *  Addition of sleep() every time the elevator goes up or down a floor
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Used for declaring checkFloor() which checks if the person's destination 
 * floor is equal to the floor that the elevator is currently at	
*/
interface LambdaForFloorCheck {
    boolean checkFloor(int personFloor, int elevatorFloor);
}
/**
 * Represents an Elevator that runs, collects Persons, and drops them off at
 * floors.
 *
 * @author Anuradha Bhave
 * @author Himanshi Chetwani
 */
public class Elevator extends Thread {

    private final static int NUMBER_OF_FLOORS = 5;
    private int currentElevatorCapacity;
    private int currentElevatorFrequency;
    private int currentPosition;
    private MyArrayList<Person> peopleInLift = new MyArrayList<>();
    private ExecutorService elevatorExecutor =
            Executors.newFixedThreadPool(10);
    private MyArrayList<Queue<Person>> floorsQueue = new MyArrayList<>();
    private List<Person> lambdaExpressionList = new ArrayList<>();
    private LambdaForFloorCheck testObject =
            (int personFloor, int elevatorFloor)-> (personFloor == elevatorFloor);


    /**
     * Default constructor
     */
    private Elevator() {
        for (int i = 1; i <= NUMBER_OF_FLOORS; i++) {
            Queue<Person> queue = new Queue<>();
            floorsQueue.add(queue);
        }
    }

    /**
     * To get sum of weight of people in the elevator
     * @return currentElevatorCapacity
     */
    private int getElevatorCapacity() {
        return currentElevatorCapacity;
    }

    private boolean checkName(Person p) {
        boolean check = false;
        for (int i = 0; i < 11; i++) {
            if (i % 2 == 0) {
                if (p.getPersonName().contains("" + i)) {
                    check = true;
                }
            }
        }
        return check;
    }

    /**
     * TO print specific details about a person
     * @param p Person
     */
    private void printInfo(Person p) {
        System.out.println("" + p.getPersonName());
        System.out.println("\t Weight: " + p.weight);
        if (p.inElevator) {
            System.out.println("\t Status: On");
            float weightContribution =
                    ((float) p.weight / (float) getElevatorCapacity()) * 100;
            System.out.printf("\t Weight Contribution: %.2f",weightContribution);
            System.out.println();
        }
        else{
            System.out.println("Status: Off");
        }
    }

    /**
     * To print details about even numbered people
     */
    private void personAndElevatorSummary() {
        lambdaExpressionList.stream().filter(this::checkName).
                forEach(this::printInfo);

    }

    /**
     * To add people to lift
     */
    private void addPeople() {
        Random r = new Random();
        for (int i = 1; i < 11; i++) {
            int randomWeight = (r.nextInt((250 - 100) + 1) + 100);
            String name = "Person-" + i;
            int randomDestination = r.nextInt
                    ((Elevator.NUMBER_OF_FLOORS - 2) + 1) + 2;
            Person p = new Person(randomWeight, name,
                    randomDestination, true);

            if (modifyElevatorCapacity(randomWeight, p.inElevator)) {
                peopleInLift.add(p);
                lambdaExpressionList.add(p);

            }
        }
        System.out.println("Number of people in the elevator: "
                + currentElevatorFrequency);
        System.out.println("Weight in elevator: " +
                currentElevatorCapacity);
    }

    /**
     * Starts the elevator, and spawns off person threads
     *
     * @throws InterruptedException could be thrown by join()
     */
    private void startElevator() throws InterruptedException {

        addPeople();
        for (int i = 0; i < peopleInLift.size(); i++) {
            Person p = peopleInLift.get(i);
            elevatorExecutor.execute(p);
        }
        sort();
        personAndElevatorSummary();
        while (peopleInLift.size() != 0) {
            goUp();
            System.out.println("Starting to go down:");
            goDown();
        }
    }


    /**
     * Checks if elevator weight has reached its maximum value
     *
     * @return true if the elevator is full
     */
    private boolean isElevatorFull() {
        int ELEVATOR_CAPACITY = 700;
        return (currentElevatorCapacity > ELEVATOR_CAPACITY);
    }


    /**
     * For the elevator to go up while dropping people in the lift at their
     * respective positions
     * @throws InterruptedException can be thrown by sleep()
     */
    private void goUp() throws InterruptedException {
        int goTill = peopleInLift.get(peopleInLift.size() - 1).destinationFloor;
        for (int i = 1; i <= goTill; i++) {
            currentPosition = i;
            for (int j = 0; j < peopleInLift.size(); j++) {
                Person p = peopleInLift.get(j);
                    if(testObject.checkFloor(p.destinationFloor,
                            currentPosition)){
                    System.out.println(p.getPersonName()
                            + " got out on floor: " + currentPosition);
                    peopleInLift.remove(j);
                    p.setInElevator(false);
                    j--;
                    modifyElevatorCapacity(p.weight,p.inElevator);
                    floorsQueue.get(currentPosition - 1).add(p);

                }
            }

            sleep(1000);
        }

    }

    /**
     * For the elevator to go down and people waiting at floors to enter the
     * elevator
     *
     * @throws InterruptedException could be thrown by sleep()
     */
    private void goDown() throws InterruptedException {
        for (int i = currentPosition; i > 0; i--) {
            currentPosition = i;
            for (int j = 0; j < peopleInLift.size(); j++) {
                Person p = peopleInLift.get(j);
                if (testObject.checkFloor(p.destinationFloor,currentPosition)) {
                    System.out.println(p.getPersonName() + " got out of the " +
                            "elevator");
                    peopleInLift.remove(j);
                    j--;
                    floorsQueue.get(currentPosition - 1).add(p);
                }
            }
            Queue<Person> currentFloorQueue =
                    floorsQueue.get(currentPosition - 1);
            if (currentFloorQueue.size() != 0) {
                while (currentFloorQueue.size() != 0) {
                    Person p = currentFloorQueue.remove();
                    if (currentPosition != 1) {
                        if (modifyElevatorCapacity(p.weight, true)) {
                            peopleInLift.add(p);
                            System.out.println("Elevator is at: " + i);
                            System.out.println(p.getPersonName() +
                                    " got added on the" + " way down");
                            p.destinationFloor = 1;
                            p.setInElevator(true);
                        }
                    }
                }
            }
            sleep(1000);
        }
    }

    /**
     * Adds to weight of elevator every time a person enters or exits
     *
     * @param weightOfPerson weight of person
     * @param entry          if the person has entered the elevator
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
     *
     * @param args None
     */
    public static void main(String[] args) throws InterruptedException {
        Elevator machine = new Elevator();
        machine.startElevator();
        machine.elevatorExecutor.shutdown();
    }

}
