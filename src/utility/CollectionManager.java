package utility;

import java.time.LocalDateTime;
import java.util.ArrayList;

import exceptions.CollectionIsEmptyException;
import data.Address;
import data.Organization;
import data.OrganizationType;

/**
 * Operates the collection itself.
 */
public class CollectionManager {
    private ArrayList<Organization> organizationCollection = new ArrayList<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileManager fileManager;

    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;

        loadCollection();
    }

    /**
     * Get the collection of organizations
     * 
     * @return An ArrayList of Organization objects.
     */
    public ArrayList<Organization> getCollection() {
        return organizationCollection;
    }

    /**
     * Get the last time the database was initialized
     * 
     * @return The last time the table was initialized.
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * Get the last time the file was saved
     * 
     * @return The last save time.
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * Get the name of type of the collection
     * 
     * @return The name of type of the collection.
     */
    public String collectionType() {
        return organizationCollection.getClass().getName();
    }

    /**
     * Get the number of organizations in the collection
     * 
     * @return The size of the organization collection.
     */
    public int collectionSize() {
        return organizationCollection.size();
    }

    /**
     * Get the first organization in the collection
     * 
     * @return The first organization in the collection.
     */
    public Organization getFirst() {
        if (organizationCollection.isEmpty())
            return null;
        return organizationCollection.get(0);
    }

    /**
     * Get the last organization in the collection
     * 
     * @return The last organization in the collection.
     */
    public Organization getLast() {
        if (organizationCollection.isEmpty())
            return null;
        return organizationCollection.get(collectionSize() - 1);
    }

    /**
     * Get an organization by its ID
     * 
     * @param id The id of the organization to retrieve.
     * @return The Organization object that has the same id as the id parameter.
     */
    public Organization getById(Long id) {
        for (Organization organization : organizationCollection) {
            if (organization.getId().equals(id)) {
                return organization;
            }
        }
        return null;
    }

    /**
     * Calculate the average annual turnover of all organizations in the collection
     * 
     * @return The average of the annual turnover of all the organizations in the
     *         collection.
     */
    public double getAverageOfAnnualTurnover() throws CollectionIsEmptyException {
        // add exception div by 0
        int totalOrganizations = collectionSize();
        if (totalOrganizations == 0)
            throw new CollectionIsEmptyException();

        double average = 0;
        double sumOfAnnualTurnover = 0;
        for (Organization organization : organizationCollection) {
            sumOfAnnualTurnover += organization.getAnnualTurnover();
        }
        average = sumOfAnnualTurnover / totalOrganizations;
        return average;
    }

    /**
     * Count the number of organizations whose official address is greater than the
     * given address
     * 
     * @param address The address to compare against.
     * @return The number of organizations with an official address that is greater
     *         than the given
     *         address.
     */
    public int getCountGreaterThanOffAddress(Address address) throws CollectionIsEmptyException {
        if (organizationCollection.isEmpty())
            throw new CollectionIsEmptyException();
        int count = 0;
        for (Organization organization : organizationCollection) {
            if (organization.getOfficialAddress().getStreet() == null)
                continue;
            if (organization.getOfficialAddress().compareTo(address) > 0) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * Get all the organizations contains given type
     * 
     * @param typeToFilter The type of organization to filter.
     * @return The organization type filtered info.
     */
    public String organizationTypeFilteredInfo(OrganizationType typeToFilter) {
        String info = "";
        for (Organization organization : organizationCollection) {
            if (organization == null)
                continue;
            if (organization.getType().equals(typeToFilter)) {
                info += organization + "\n\n";
            }
        }
        return info.trim();
    }

    /**
     * Add an organization to the collection of organizations
     * 
     * @param organization The organization to add to the collection.
     */
    public void addToCollection(Organization organization) {
        organizationCollection.add(organization);
    }

    /**
     * Remove an organization from the collection
     * 
     * @param organization The organization to be removed from the collection.
     */
    public void removeFromCollection(Organization organization) {
        organizationCollection.remove(organization);
    }

    /**
     * Remove all organizations from the collection that are greater than the
     * organization passed in
     * 
     * @param organizationToCompare The organization to compare to the current
     *                              organization.
     */
    public void removeGreater(Organization organizationToCompare) {
        organizationCollection.removeIf(organization -> organization.compareTo(organizationToCompare) > 0);
    }

    /**
     * Clear all organization in the collection.
     */
    public void clearCollection() {
        organizationCollection.clear();
    }

    /**
     * Generates next ID. It will be (the bigger one + 1).
     * 
     * @return Next ID.
     */
    public Long generateNextId() {
        if (organizationCollection.isEmpty())
            return 1L;
        return getLast().getId() + 1;
    }

    /**
     * Saves the collection to file.
     */
    public void saveCollection() {
        fileManager.writeCollection(organizationCollection);
        lastSaveTime = LocalDateTime.now();
    }

    /**
     * Loads the collection from file.
     */
    private void loadCollection() {
        organizationCollection = fileManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        if (organizationCollection.isEmpty())
            return "The collection is empty!";

        String info = "";
        for (Organization organization : organizationCollection) {
            info += organization;
            if (organization != getLast())
                info += "\n\n";
        }
        return info;
    }
}
