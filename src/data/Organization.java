package data;

import java.time.LocalDateTime;

/**
 * An organization is a type of entity that has a name, coordinates, creation date, annual turnover and
 * a type.
 */
public class Organization implements Comparable<Organization> {
    private long id; 
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDateTime creationDate; 
    private long annualTurnover; 
    private OrganizationType type; 
    private Address officialAddress; 

    public Organization(long id, String name, Coordinates coordinates, LocalDateTime creationDate, long annualTurnover,
            OrganizationType type, Address officialAddress) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.annualTurnover = annualTurnover;
        this.type = type;
        this.officialAddress = officialAddress;
    }

    public void setId(long id){
        this.id = id;
    }

    /**
     * Get the ID of the organization
     * 
     * @return The id of the organization.
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the name of the organization
     * 
     * @return The name of the organization.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the coordinates of the organization
     * 
     * @return The coordinates of the organization.
     */

    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Get the creation date of the organization
     * 
     * @return The creation date of the organization.
     */
    public java.time.LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Returns the annual turnover of the organization
     * 
     * @return The annual turnover of the organization.
     */
    public Long getAnnualTurnover() {
        return annualTurnover;
    }

    /**
     * Returns the type of the organization
     * 
     * @return The type of the organization.
     */
    public OrganizationType getType() {
        return type;
    }

    /**
     * Returns the official address of the organization
     * 
     * @return The official address of the organization.
     */
    public Address getOfficialAddress() {
        return officialAddress;
    }

    @Override
    public int compareTo(Organization organizationToCompare) {
        return ((Long) annualTurnover).compareTo(organizationToCompare.getAnnualTurnover());
    }

    @Override
    public String toString() {
        String info = "";
        info += "Organization ID: " + id;
        info += " (added " + creationDate.toLocalDate() + " " + creationDate.toLocalTime() + ")";
        info += "\n Name: " + name;
        info += "\n Coordinates: " + coordinates;
        info += "\n Annual turnover: " + annualTurnover;
        info += "\n Organization Type: " + type;
        info += "\n Official address: " + officialAddress;
        return info;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + creationDate.hashCode() + coordinates.hashCode() + ((Long) annualTurnover).hashCode()
                + type.hashCode() + officialAddress.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Organization) {
            Organization other = (Organization) obj;
            return (name.equals(other.getName())) && coordinates.equals(other.getCoordinates())
                    && (annualTurnover == other.getAnnualTurnover()) && (type.equals(other))
                    && (officialAddress.equals(other.getOfficialAddress()));
        }
        return false;
    }
}
