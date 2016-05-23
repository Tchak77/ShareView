package shapes;


public class Point {
    private float x;
    private float y;


    /**
     * Represents a Point into a Polyline
     * @param x Coordinates on the X axis
     * @param y Coordinates on the Y axis
     */
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Return the value on the X axis
     * @return X
     */
    public float getX() {
        return x;
    }

    /**
     * Return the value on Y axis
     * @return Y
     */
    public float getY() {
        return y;
    }


    /**
     * Move the Point on the view
     * @param dx Value of the translation on X axis
     * @param dy Value of the translation on Y axis
     */
    public void translate(int dx, int dy){
        x += dx;
        y += dy;
    }
}
