package oopwithjava;

/**
 * Represents a contact with an associated department and email.
 * This class holds and validates the email and department information for a
 * contact.
 * 
 * @author Altay Ozkan
 */
public class Contact {
    private Department department;
    private String email;

    /**
     * Initializes a new instance of the Contact class.
     * 
     * @param department the department associated with this contact
     * @param email      the email associated with this contact
     */
    public Contact(Department department, String email) {
        this.department = department;
        this.email = email;
    }

    /**
     * Checks if the contact information is valid.
     * Validation is performed based on the department and email.
     * 
     * @return true if the contact information is valid, false otherwise
     */
    public boolean isValid() {
        if (department == null) {
            return false;
        }
        String emailLowerCase = email.toLowerCase();
        String departmentLowerCase = department.name().toLowerCase();
        return emailLowerCase.equals(departmentLowerCase + "@rutgers.edu");
    }

    /**
     * Retrieves the department associated with this contact.
     * 
     * @return the department of this contact
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Retrieves the email associated with this contact.
     * 
     * @return the email of this contact
     */
    public String getEmail() {
        return email;
    }
}
