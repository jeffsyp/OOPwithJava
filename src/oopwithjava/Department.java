package oopwithjava;

/**
 * Represents various departments with their full names.
 * 
 * @author Altay Ozkan
 */
public enum Department {
    BAIT("Business Analytics and Information Technology"),
    CS("Computer Science"),
    EE("Electrical Engineering"),
    ITI("Information Technology and Informatics"),
    MATH("Mathematics");

    private final String fullName;

    /**
     * Initializes a new instance of the Department enum with a specific full name.
     * 
     * @param fullName the full name of the department
     */
    private Department(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Returns the full name of the department.
     * 
     * @return the full name of the department
     */
    public String getFullName() {
        return fullName;
    }
}
