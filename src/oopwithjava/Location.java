package oopwithjava;

/**
 * Represents the locations where events can be held.
 * Each location includes a building and a campus.
 *
 * @author Jeffery Sypytkowski
 * @author Altay Ozkan
 */
public enum Location {
    HLL114("Hill Center", "Busch"),
    ARC103("Allison Road Classroom", "Busch"),
    AB2225("Academic Building", "College Avenue"),
    MU302("Murray Hall", "College Avenue"),
    BE_AUD("Beck Hall", "Livingston"),
    TIL232("Tillett Hall", "Livingston");

    private final String building;
    private final String campus;

    /**
     * Constructs a Location with the given building and campus.
     *
     * @param building the name of the building
     * @param campus   the name of the campus
     */
    private Location(String building, String campus) {
        this.building = building;
        this.campus = campus;
    }

    /**
     * Retrieves the building of this location.
     *
     * @return the building name as a String
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Retrieves the campus of this location.
     *
     * @return the campus name as a String
     */
    public String getCampus() {
        return campus;
    }
}
