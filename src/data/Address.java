package data;

/**
 * * The class Address represents an address.
 */
public class Address {
    private String street; // Строка не может быть пустой, Поле может быть null
    private String zipCode; // Поле не может быть null

    public Address(String street, String zipCode) {
        this.street = street;
        this.zipCode = zipCode;
    }

    /**
     * Get the street name of the address
     * 
     * @return The street property of the address object.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Get the zipcode of the address
     * 
     * @return The zip code.
     */
    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "Address [street=" + street + ", zipCode=" + zipCode + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        if (street == null) {
            if (other.street != null)
                return false;
        } else if (!street.equals(other.street))
            return false;
        if (zipCode == null) {
            if (other.zipCode != null)
                return false;
        } else if (!zipCode.equals(other.zipCode))
            return false;
        return true;
    }

    public int compareTo(Address address) {
        int standard = address.getStreet().length();
        if (street.length() == standard)
            return 0;
        else if (street.length() > standard)
            return 1;
        else
            return -1;
    }

}
