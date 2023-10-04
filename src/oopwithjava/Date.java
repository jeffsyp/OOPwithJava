package oopwithjava;

import java.util.Calendar;

/**
 * Represents a date, allowing for validation, comparison, and text
 * representation.
 * 
 * @author Altay Ozkan
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Initializes a new instance of the Date class with a specific date.
     * 
     * @param date a string representing a date in the form "mm/dd/yyyy"
     */
    public Date(String date) {
        String[] dateParts = date.split("/");
        if (dateParts.length == 3) {
            try {
                this.month = Integer.parseInt(dateParts[0]);
                this.day = Integer.parseInt(dateParts[1]);
                this.year = Integer.parseInt(dateParts[2]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid calendar date!");
            }
        } else {
            System.err.println("Invalid calendar date!");
        }
    }

    /**
     * Initializes a new instance of the Date class with today's date.
     */
    public Date() {
        Calendar today = Calendar.getInstance();
        this.month = today.get(Calendar.MONTH) + 1;
        this.day = today.get(Calendar.DAY_OF_MONTH);
        this.year = today.get(Calendar.YEAR);
    }

    /**
     * Initializes a new instance of the Date class, copying the provided date.
     * 
     * @param date the date to clone
     */
    public Date(Date date) {
        this.month = date.month;
        this.day = date.day;
        this.year = date.year;
    }

    /**
     * Compares this date with another date.
     * 
     * @param date the date to compare to
     * @return a negative integer, zero, or a positive integer as this date is less
     *         than, equal to, or greater than the specified date
     */
    @Override
    public int compareTo(Date date) {
        if (this.year != date.year) {
            return Integer.compare(this.year, date.year);
        } else if (this.month != date.month) {
            return Integer.compare(this.month, date.month);
        } else if (this.day != date.day) {
            return Integer.compare(this.day, date.day);
        } else {
            return 0;
        }
    }

    /**
     * Returns today's date.
     * 
     * @return a Date object representing today's date
     */
    public static Date today() {
        return new Date();
    }

    /**
     * Checks if this date object represents a valid calendar date.
     * 
     * @return true if this date is a valid calendar date, false otherwise
     */
    public boolean isValid() {
        if (month < 1 || month > Constants.MONTHS_COUNT || day < 1 || year < 0) {
            return false;
        }
        int maxDay = Constants.DAYS_31;
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                maxDay = Constants.DAYS_30;
                break;
            case 2:
                if (year % Constants.QUATERCENTENNIAL == 0
                        || (year % Constants.CENTENNIAL != 0 && year % Constants.QUADRENNIAL == 0)) {
                    maxDay = Constants.DAYS_29;
                } else {
                    maxDay = Constants.DAYS_28;
                }
                break;
        }
        return day <= maxDay;
    }

    /**
     * Returns the textual representation of this date.
     * 
     * @return a string representing this date in the form "mm/dd/yyyy"
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /**
     * Getter method for the day of a date
     * 
     * @return day of date
     */
    public int getDay() {
        return day;
    }

    /**
     * Getter method for the month of a date
     * 
     * @return month of date
     */
    public int getMonth() {
        return month;
    }

    /**
     * Getter method for the year of a date
     * 
     * @return year of date
     */
    public int getYear() {
        return year;
    }

    /**
     * Check if two dates are the same date
     * 
     * @return true if they are the same date
     * @return false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        Date date = (Date) obj;
        return this.year == date.year && this.month == date.month && this.day == date.day;
    }

    /**
     * Executes various test cases to check the validity of dates.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        testDaysInFeb_Nonleap();
        testDaysInFeb_Leap();
        testMonth_OutOfRange();
    }

    /**
     * Test case 1
     */
    private static void testDaysInFeb_Nonleap() {
        Date date = new Date("2/29/2011"); // test data
        boolean expectedOutput = false; // define expected output
        boolean actualOutput = date.isValid(); // call the method to get the actual output
        System.out.println("**Test case #1: # of days in Feb. in a non-leap year is 28");
        testResult(date, expectedOutput, actualOutput); // compare the results
    }

    /**
     * Test case 2
     */
    private static void testDaysInFeb_Leap() {
        Date date = new Date("2/29/2012"); // test data for a leap year
        boolean expectedOutput = true; // define expected output
        boolean actualOutput = date.isValid(); // call the method to get the actual output
        System.out.println("**Test case #2: # of days in Feb. in a leap year can be 29");
        testResult(date, expectedOutput, actualOutput); // compare the results
    }

    /**
     * Test case 3
     */
    private static void testMonth_OutOfRange() {
        Date date = new Date("13/1/2011"); // test data
        boolean expectedOutput = false; // define expected output
        boolean actualOutput = date.isValid(); // call the method to get the actual output
        System.out.println("**Test case #3: Month out of range");
        testResult(date, expectedOutput, actualOutput); // compare the results
    }

    /**
     * Check if a test case is PASS or FAIL
     */
    private static void testResult(Date date, boolean expectedOutput, boolean actualOutput) {
        if (expectedOutput == actualOutput) {
            System.out.println("PASS. Date: " + date + ", Valid: " + actualOutput);
        } else {
            System.out.println("FAIL. Date: " + date + ", Expected: " + expectedOutput + ", Actual: " + actualOutput);
        }
    }
}