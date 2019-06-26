/*
 * Name:
 *  Person.java
 *
 * Version:
 *  1.1
 *
 * Revisions:
 *  run() method puts thread to sleep
 *  inElevator is volatile
 *  Added getinElevator(), setinElevator()
 */



/**
 * This class describes a person in the elevator
 *
 * @author Anuradha Bhave
 * @author Himanshi Chetwani
 */
public class Person extends Thread {
    int weight;
    private String name;
    int destinationFloor;
    volatile boolean inElevator;


    /**
     * Parameterized constructor
     *
     * @param weightOfPerson weight in pounds
     * @param nameOfPerson   name of person
     * @param elevatorEntry  floor where person wants to get off
     */
    Person(int weightOfPerson, String nameOfPerson, int destination,
           boolean elevatorEntry) {
        this.weight = weightOfPerson;
        this.name = nameOfPerson;
        this.destinationFloor = destination;
        this.inElevator = elevatorEntry;
    }

    void setInElevator(boolean entry) {
        this.inElevator = entry;
    }

    /**
     * Returns name
     *
     * @return string which is person's name
     */
    String getPersonName() {
        return this.name;
    }

    /**
     * The run method which sets the person's destination floor
     */
    public void run() {
        boolean exitFlag = false;
        while (!exitFlag) {
            if (!getinElevator()) {
                try {
                    System.out.println(getPersonName()+" "+currentThread() +
                            " is working");
                    sleep(3000);
                    exitFlag = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private boolean getinElevator() {
        return this.inElevator;
    }
}