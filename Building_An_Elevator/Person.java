/*
 * Name:
 *  Person.java
 *
 * Version:
 *  1.0
 *
 * Revisions:
 *  None
 */
import java.util.Random;

/**
 * This class describes a person in the elevator
 *
 * @author Anuradha Bhave
 * @author Himanshi Chetwani
 */
public class Person extends Thread {
    private int weight;
    private String name;
    int sourceFloor;
    int destinationFloor;
    boolean entry;

    /**
     * Parameterized constructor
     * @param weightOfPerson weight in pounds
     * @param nameOfPerson name of person
     * @param source floor where person is
     * @param elevatorEntry floor where person wants to get off
     */
    Person(int weightOfPerson, String nameOfPerson,int source,
           boolean elevatorEntry) {
        this.weight = weightOfPerson;
        this.name = nameOfPerson;
        this.sourceFloor = source;
        this.entry = elevatorEntry;
    }


    /**
     * Returns name
     * @return string which is person's name
     */
    String getPersonName() {return this.name;}

    /**
     * The run method which sets the person's destination floor
     */
    public void run(){
        Random r = new Random();
        this.destinationFloor = r.nextInt((Elevator.NUMBER_OF_FLOORS - 1)
                + 1) + 1;
    }

}