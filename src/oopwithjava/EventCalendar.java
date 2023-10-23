package oopwithjava;

/**
 * Represents an Event Calendar, holding and managing a list of events.
 * Provides functionalities to add, remove, and print events, and sort events by
 * date, campus, and department.
 *
 * @author Jeffery Sypytkowski
 * @author Altay Ozkan
 */

public class EventCalendar {
    private Event[] events;
    private int numEvents;

    /**
     * Initializes an empty Event Calendar with an initial capacity.
     */
    public EventCalendar() {
        this.events = new Event[Constants.INITIAL_CAPACITY];
        this.numEvents = 0;
    }

    /**
     * Finds the index of the specified event in the events array.
     *
     * @param event the event to be searched for.
     * @return the index of the event, or Constants.NOT_FOUND if the event is not
     *         found.
     */
    private int find(Event event) {
        for (int i = 0; i < numEvents; i++) {
            if (events[i].equals(event)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    /**
     * Increases the size of the events array.
     */
    private void grow() {
        Event[] newEvents = new Event[events.length + Constants.GROWTH_AMOUNT];
        for (int i = 0; i < numEvents; i++) {
            newEvents[i] = events[i];
        }
        events = newEvents;
    }

    /**
     * Adds a new event to the events array.
     *
     * @param event the event to be added.
     */
    public void add(Event event) {
        if (numEvents >= events.length) {
            grow();
        }
        events[numEvents] = event;
        numEvents++;
    }

    /**
     * Removes a specified event from the events array.
     *
     * @param event the event to be removed.
     * @return true if the event was successfully removed, false otherwise.
     */
    public boolean remove(Event event) {
        int index = find(event);
        if (index == Constants.NOT_FOUND)
            return false;

        for (int i = index; i < numEvents - 1; i++) {
            events[i] = events[i + 1];
        }
        numEvents--;
        events[numEvents] = null;
        return true;
    }

    /**
     * Checks if a specified event is present in the events array.
     *
     * @param event the event to check for.
     * @return true if the event is found, false otherwise.
     */
    public boolean contains(Event event) {
        return find(event) != Constants.NOT_FOUND;
    }

    /**
     * Prints all events in the events array in the current order.
     */
    public void print() {
        if (events[0] == null)
            System.out.println("Event calendar is empty!");
        else {
            System.out.println("* Event calendar *");
            for (int i = 0; i < numEvents; i++) {
                System.out.println(events[i]);
            }
            System.out.println("* end of event calendar *");
        }
    }

    /**
     * Sorts the events array by date.
     */
    private void sortByDate() {
        for (int i = 0; i < numEvents - 1; i++) {
            for (int j = 0; j < numEvents - i - 1; j++) {
                if (events[j].compareTo(events[j + 1]) > 0) {
                    Event temp = events[j];
                    events[j] = events[j + 1];
                    events[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Prints the events array by date.
     */
    public void printByDate() {
        if (events[0] == null)
            System.out.println("Event calendar is empty!");
        else {
            System.out.println("* Event calendar by event date and start time *");
            sortByDate();
            for (int i = 0; i < numEvents; i++) {
                System.out.println(events[i]);
            }
            System.out.println("* end of event calendar *");
        }
    }

    /**
     * Sorts the events array by location.
     */
    private void sortByCampus() {
        for (int i = 0; i < numEvents - 1; i++) {
            for (int j = 0; j < numEvents - i - 1; j++) {
                String campus1 = events[j].getLocation().getCampus();
                String campus2 = events[j + 1].getLocation().getCampus();
                int cmpCampus = campus1.compareTo(campus2);
                if (cmpCampus > 0) {
                    Event temp = events[j];
                    events[j] = events[j + 1];
                    events[j + 1] = temp;
                } else if (cmpCampus == 0) {
                    String building1 = events[j].getLocation().getBuilding();
                    String building2 = events[j + 1].getLocation().getBuilding();
                    int cmpBuilding = building1.compareTo(building2);
                    if (cmpBuilding > 0) {
                        Event temp = events[j];
                        events[j] = events[j + 1];
                        events[j + 1] = temp;
                    }
                }
            }
        }
    }

    /**
     * Prints the events array by location.
     */
    public void printByCampus() {
        if (events[0] == null)
            System.out.println("Event calendar is empty!");
        else {
            System.out.println("* Event calendar by campus and building *");
            sortByCampus();
            for (int i = 0; i < numEvents; i++) {
                System.out.println(events[i]);
            }
            System.out.println("* end of event calendar *");
        }
    }

    /**
     * Sorts the events array by department.
     */
    private void sortByDepartment() {
        for (int i = 0; i < numEvents - 1; i++) {
            for (int j = 0; j < numEvents - i - 1; j++) {
                String department1 = events[j].getContact().getDepartment().getFullName();
                String department2 = events[j + 1].getContact().getDepartment().getFullName();
                int cmp = department1.compareTo(department2);
                if (cmp > 0) {
                    Event temp = events[j];
                    events[j] = events[j + 1];
                    events[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Prints the events array by department.
     */
    public void printByDepartment() {
        if (events[0] == null)
            System.out.println("Event calendar is empty!");
        else {
            System.out.println("* Event calendar by department *");
            sortByDepartment();
            for (int i = 0; i < numEvents; i++) {
                System.out.println(events[i]);
            }
            System.out.println("* end of event calendar *");
        }
    }

    /**
     * Gets the array of events.
     *
     * @return the events array.
     */
    public Event[] getEvents() {
        return events;
    }
}
