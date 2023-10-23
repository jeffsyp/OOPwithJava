package oopwithjava;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * EventOrganizer is responsible for adding, removing and managing events in the
 * calendar.
 * It processes the user commands for various operations related to events.
 *
 * @author Jeffery Sypytkowski
 * @author Altay Ozkan
 */
public class EventOrganizer {
    private Scanner scanner;
    private boolean running;
    private EventCalendar eventCalendar;

    /**
     * Initializes the EventOrganizer by setting up the necessary components.
     */
    public EventOrganizer() {
        scanner = new Scanner(System.in);
        running = false;
        eventCalendar = new EventCalendar();
    }

    /**
     * Starts the Event Organizer application.
     * Continuously processes user input commands until the exit command is given.
     */
    public void run() {
        running = true;
        System.out.println("Event Organizer running...");

        while (running) {
            String commandLine = scanner.nextLine();
            processCommand(commandLine);
        }

        System.out.println("Event Organizer terminated.");
    }

    /**
     * Processes the user input command.
     *
     * @param commandLine the input command line from the user
     */
    private void processCommand(String commandLine) {
        StringTokenizer tokenizer = new StringTokenizer(commandLine);
        if (tokenizer.hasMoreTokens()) {
            String command = tokenizer.nextToken();
            switch (command) {
                case "Q" -> running = false;
                case "A" -> addEvent(tokenizer);
                case "R" -> removeEvent(tokenizer);
                case "P" -> eventCalendar.print();
                case "PE" -> eventCalendar.printByDate();
                case "PC" -> eventCalendar.printByCampus();
                case "PD" -> eventCalendar.printByDepartment();
                default -> System.out.println(command + " is an invalid command!");
            }
        }
    }

    /**
     * Adds an event to the calendar after performing various validation checks.
     *
     * @param tokenizer the tokenizer containing the event details
     * @return if event not added
     */
    private void addEvent(StringTokenizer tokenizer) {
        Date newDate = new Date(tokenizer.nextToken());
        if (!dateCheck(newDate))
            return;
        Timeslot newTimeslot = getEnumValue(tokenizer.nextToken().toUpperCase(), Timeslot.class);
        if (newTimeslot == null) {
            System.out.println("Invalid time slot!");
            return;
        }
        Location newLocation = getEnumValue(tokenizer.nextToken().toUpperCase(), Location.class);
        if (newLocation == null) {
            System.out.println("Invalid location!");
            return;
        }
        Contact newContact = new Contact(getEnumValue(tokenizer.nextToken().toUpperCase(), Department.class),
                tokenizer.nextToken());
        if (!newContact.isValid()) {
            System.out.println("Invalid contact information!");
            return;
        }
        Event newEvent = new Event(newDate, newTimeslot, newLocation, newContact,
                Integer.parseInt(tokenizer.nextToken()));
        if (conflict(newEvent, eventCalendar)) {
            System.out.println("The event is already on the calendar.");
            return;
        }
        if (newEvent.getDuration() < 30 || newEvent.getDuration() > 120) {
            System.out.println("Event duration must be at least 30 minutes and at most 120 minutes");
        } else {
            eventCalendar.add(newEvent);
            System.out.println("Event added to the calendar.");
        }
    }

    /**
     * Removes an event from the calendar based on event details.
     *
     * @param tokenizer the tokenizer containing the event details for removal
     * @return if date not removed
     */
    private void removeEvent(StringTokenizer tokenizer) {
        try {
            Date removeDate = new Date(tokenizer.nextToken());
            // Adding the dateCheck here similar to addEvent method
            if (!dateCheck(removeDate))
                return;
            Timeslot removeTimeslot = getEnumValue(tokenizer.nextToken().toUpperCase(), Timeslot.class);
            if (removeTimeslot == null) {
                System.out.println("Invalid time slot!");
                return;
            }
            Location removeLocation = getEnumValue(tokenizer.nextToken().toUpperCase(), Location.class);
            if (removeLocation == null) {
                System.out.println("Invalid location!");
                return;
            }

            // temporary event object
            Event removeEvent = new Event(removeDate, removeTimeslot, removeLocation, null, 0);

            // Remove the event from event calendar
            boolean removed = eventCalendar.remove(removeEvent);
            if (removed) {
                System.out.println("Event has been removed from the calendar!");
            } else {
                System.out.println("Cannot remove; event is not in the calendar!");
            }
        } catch (Exception e) {
            System.out.println("Error parsing event details for removal.");
        }
    }

    /**
     * Checks if the new event conflicts with existing events in the calendar.
     *
     * @param newEvent      the new event to check for conflicts
     * @param eventCalendar the event calendar to check the new event against
     * @return true if there is a conflict, false otherwise
     */
    private static boolean conflict(Event newEvent, EventCalendar eventCalendar) {
        for (Event e : eventCalendar.getEvents()) {
            if (e == null) {
                return false;
            }
            if (e.getDate().equals(newEvent.getDate()) && e.getTimeslot().equals(newEvent.getTimeslot())
                    && e.getLocation().equals(newEvent.getLocation())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if date is valid and within the upcoming 6 months
     *
     * @return true if date is valid
     *
     * @return false if date is invalid
     */
    private Boolean dateCheck(Date date) {
        if (!date.isValid()) {
            System.out.println(date + ": Invalid calendar date!");
            return false;
        }
        Date todayDate = new Date();
        if (date.compareTo(todayDate) < 0) {
            System.out.println(date + ": Event date must be a future date!");
            return false;
        }
        if ((date.getYear() == todayDate.getYear()) && (date.getMonth() == todayDate.getMonth() + 6)
                && date.getDay() > todayDate.getDay()) {
            System.out.println(date + ": Event date must be within 6 months!");
            return false;
        }
        if (date.getYear() == todayDate.getYear() && date.getMonth() > todayDate.getMonth() + 6) {
            System.out.println(date + ": Event date must be within 6 months!");
            return false;
        }
        if ((date.getYear() == todayDate.getYear() + 1) && (date.getMonth() == (todayDate.getMonth() + 6) % 12)
                && date.getDay() > todayDate.getDay()) {
            System.out.println(date + ": Event date must be within 6 months!");
            return false;
        }
        if ((date.getYear() == todayDate.getYear() + 1) && (date.getMonth() > (todayDate.getMonth() + 6) % 12)) {
            System.out.println(date + ": Event date must be within 6 months!");
            return false;
        }
        if (date.getYear() > todayDate.getYear() + 1) {
            System.out.println(date + ": Event date must be within 6 months!");
            return false;
        }
        return true;
    }

    /**
     * Retrieves an enum value from a string input.
     *
     * @param <T>       the type of the enum
     * @param input     the string input to convert to enum
     * @param enumClass the class of the enum
     * @return the enum value corresponding to the input string, or null if not
     *         found
     */
    private static <T extends Enum<T>> T getEnumValue(String input, Class<T> enumClass) {
        for (T enumValue : enumClass.getEnumConstants()) {
            if (enumValue.name().equals(input)) {
                return enumValue;
            }
        }
        return null;
    }
}
