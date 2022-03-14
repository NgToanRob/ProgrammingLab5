package data;

/**
 * Coordinates is a class that has two fields: x and y
 */
public class Coordinates {
    private int x; // Значение поля должно быть больше -396
    private float y; // Максимальное значение поля: 969

    public Coordinates(int x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate of the organization
     * 
     * @return The x coordinate of the organization.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the organization
     * 
     * @return The y-coordinate of the organization.
     */
    public float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "X:" + x + " Y:" + y;
    }

    @Override
    public int hashCode() {
        return ((Float) y).hashCode() + (int) x;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Coordinates) {
            Coordinates coordinatesObj = (Coordinates) obj;
            return (x == coordinatesObj.getX()) && ((Float) y).equals(coordinatesObj.getY());
        }
        return false;
    }
}
