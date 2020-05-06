import game.Vector2D;

public class Rectangle {
    public Vector2D position;
    public int width;
    public int height;

    public Rectangle(Vector2D position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public Rectangle(double x, double y, int width, int height) {
        this(new Vector2D(x, y), width, height);
    }

    public double top() {
        return position.y;
    }

    public double bot() {
        return top() + height;
    }

    public double left() {
        return position.x;
    }

    public double right() {
        return left() + width;
    }

    /**
     * kiem tra xem 2 hinh chu nhat: 1 la this, 2 la other
     *
     * this co giao vs other
     * neu co >> return true
     * neu ko >> return false
     * @param other
     * @return
     */
    public boolean intersects(Rectangle other) {
        return other.right() >= this.left()
                && other.left() <= this.right()
                && other.bot() >= this.top()
                && other.top() <= this.bot();
    }

    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(0, 0, 1, 1);
        Rectangle r2 = new Rectangle(1, 1, 2, 2);
        Rectangle r3 = new Rectangle(-3, -3, 5, 5);
        System.out.println(r1.intersects(r2)); // true
    }
}
