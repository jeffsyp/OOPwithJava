package oopwithjava;

/**
 * Represents an event with date, time, location, contact and duration details.
 *
 * @author Altay Ozkan
 */
public class Event implements Comparable<Event> {
    private Date date; // the event date
    private Timeslot startTime; // the starting time
    private Location location;
    private Contact contact; // include the department name and email
    private int duration; // in minutes

    /**
     * Initializes a new instance of the Event class.
     *
     * @param date      the date of the event
     * @param startTime the start time of the event
     * @param location  the location of the event
     * @param contact   the contact details for the event
     * @param duration  the duration of the event in minutes
     */
    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    /**
     * Getter method for Date
     * 
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Getter method for Timeslot
     * 
     * @return startTime
     */
    public Timeslot getTimeslot() {
        return startTime;
    }

    /**
     * Getter method for Location
     * 
     * @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Getter method for Contact
     * 
     * @return contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Getter method for Duration
     * 
     * @return duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Tests the equality of this event with another object.
     *
     * @param obj the object to compare with
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        Event event = (Event) obj;
        return this.date.equals(event.date) && this.startTime.equals(event.startTime)
                && this.location.equals(event.location);
    }

    /**
     * Returns a string representation of the event.
     *
     * @return a string representation of the event
     */
    @Override
    public String toString() {
        return "[Event Date: " + date + "] [Start: " + startTime.getHour() + ":" + startTime.getMinute()
                + startTime.getAMorPM() + "] [End: " + startTime.getEndTime(duration) + "] @" + location.name() + " ("
                + location.getBuilding() + ", " + location.getCampus() + ") [Contact: "
                + contact.getDepartment().getFullName() + ", " + contact.getEmail() + "]";
    }

    /**
     * Compares this event with another event.
     *
     * @param otherEvent the other event to compare with
     * @return a negative integer, zero, or a positive integer as this event is less
     *         than, equal to, or greater than the specified event.
     */
    @Override
    public int compareTo(Event otherEvent) {
        int dateCompare = date.compareTo(otherEvent.date);
        if (dateCompare == 0) {
            return startTime.compareTo(otherEvent.startTime);
        }
        return dateCompare;
    }

    /**
     * The main method that tests the equals() method.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        testEventEquality_Equal();
        testEventEquality_Date();
        testEventEquality_Timeslot();
        testEventEquality_Location();
    }

    /**
     * Test 1
     */
    private static void testEventEquality_Equal() {
        Date date = new Date("09/30/2023");
        Timeslot time = Timeslot.MORNING;
        Location location = Location.HLL114;
        Contact contact = new Contact(Department.CS, "cs@rutgers.edu");
        Event event1 = new Event(date, time, location, contact, 60);

        Date anotherDate = new Date("09/30/2023");
        Timeslot anotherTime = Timeslot.MORNING;
        Location anotherLocation = Location.HLL114;
        Contact anotherContact = new Contact(Department.CS, "cS@rutgers.edu");
        Event event2 = new Event(anotherDate, anotherTime, anotherLocation, anotherContact, 60);

        System.out.println("**Test case #1: Event Equality");
        testResult("Event Equality", true, event1.equals(event2));
    }

    /**
     * Test 2
     */
    private static void testEventEquality_Date() {
        Date date = new Date("09/28/2023");
        Timeslot time = Timeslot.MORNING;
        Location location = Location.HLL114;
        Contact contact = new Contact(Department.CS, "cs@rutgers.edu");
        Event event1 = new Event(date, time, location, contact, 60);

        Date anotherDate = new Date("09/30/2023");
        Timeslot anotherTime = Timeslot.MORNING;
        Location anotherLocation = Location.HLL114;
        Contact anotherContact = new Contact(Department.CS, "cs@rutgers.edu");
        Event event2 = new Event(anotherDate, anotherTime, anotherLocation, anotherContact, 60);

        System.out.println("**Test case #1: Event Equality");
        testResult("Event Equality", false, event1.equals(event2));
    }

    /**
     * Test 3
     */
    private static void testEventEquality_Timeslot() {
        Date date = new Date("09/30/2023");
        Timeslot time = Timeslot.MORNING;
        Location location = Location.HLL114;
        Contact contact = new Contact(Department.CS, "cs@rutgers.edu");
        Event event1 = new Event(date, time, location, contact, 60);

        Date anotherDate = new Date("09/30/2023");
        Timeslot anotherTime = Timeslot.AFTERNOON;
        Location anotherLocation = Location.HLL114;
        Contact anotherContact = new Contact(Department.CS, "cs@rutgers.edu");
        Event event2 = new Event(anotherDate, anotherTime, anotherLocation, anotherContact, 60);

        System.out.println("**Test case #1: Event Equality");
        testResult("Event Equality", false, event1.equals(event2));
    }

    /**
     * Test 4
     */
    private static void testEventEquality_Location() {
        Date date = new Date("09/30/2023");
        Timeslot time = Timeslot.MORNING;
        Location location = Location.HLL114;
        Contact contact = new Contact(Department.CS, "cs@rutgers.edu");
        Event event1 = new Event(date, time, location, contact, 60);

        Date anotherDate = new Date("09/30/2023");
        Timeslot anotherTime = Timeslot.MORNING;
        Location anotherLocation = Location.BE_AUD;
        Contact anotherContact = new Contact(Department.CS, "cs@rutgers.edu");
        Event event2 = new Event(anotherDate, anotherTime, anotherLocation, anotherContact, 60);

        System.out.println("**Test case #1: Event Equality");
        testResult("Event Equality", false, event1.equals(event2));
    }

    /**
     * Test result
     */
    private static void testResult(String testName, boolean expected, boolean actual) {
        if (expected == actual) {
            System.out.println("PASS Test " + testName + ": Expected: " + expected + ", Actual: " + actual);
        } else {
            System.out.println("FAIL Test " + testName + ": Expected: " + expected + ", Actual: " + actual);
        }
    }
}
