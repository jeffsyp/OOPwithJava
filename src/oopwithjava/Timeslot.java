package oopwithjava;

/**
 * Represents the different time slots available for events.
 * Each time slot is defined by a start hour and minute.
 *
 * @author Jeffery Sypytkowski
 * @author Altay Ozkan
 */
public enum Timeslot {
    MORNING("10", "30"),
    AFTERNOON("2", "00"),
    EVENING("6", "30");

    private final String hour;
    private final String minute;

    /**
     * Constructs a Timeslot with specified hour and minute.
     *
     * @param hour   the starting hour of the timeslot
     * @param minute the starting minute of the timeslot
     */
    private Timeslot(String hour, String minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Gets the starting hour of the timeslot.
     *
     * @return the starting hour as a string
     */
    public String getHour() {
        return hour;
    }

    /**
     * Gets the starting minute of the timeslot.
     *
     * @return the starting minute as a string
     */
    public String getMinute() {
        return minute;
    }

    /**
     * Determines if the timeslot is in AM or PM.
     *
     * @return "AM" or "PM" as a string
     */
    public String getAMorPM() {
        int hourInt = Integer.parseInt(hour);
        if (hourInt >= Constants.AM_OPEN && hourInt < Constants.AM_CLOSE)
            return "am";
        else
            return "pm";
    }

    /**
     * Determines if the specified hour is in AM or PM.
     *
     * @param hour the hour to check
     * @return "AM" or "PM" as a string
     */
    public String getAMorPM(int hour) {
        if (hour >= Constants.AM_OPEN && hour < Constants.AM_CLOSE)
            return "am";
        else
            return "pm";
    }

    /**
     * Calculates the end time of an event based on its duration.
     *
     * @param duration the duration of the event in minutes
     * @return the end time as a formatted string
     */
    public String getEndTime(int duration) {
        int currentHour = Integer.parseInt(hour);
        int currentMinute = Integer.parseInt(minute);
        int newMinute = (currentMinute + duration) % Constants.MINUTES_PER_HOUR;
        int hoursAdded = (currentMinute + duration) / Constants.MINUTES_PER_HOUR;
        int newHour = (currentHour + hoursAdded);

        String newHourString = String.format("%d", newHour);
        String newMinuteString = String.format("%02d", newMinute);

        return newHourString + ":" + newMinuteString + getAMorPM(newHour);
    }
}
