/*
 * Name:
 *  Ship.java
 * Version:
 *  1.0
 * Revisions:
 *  None
 */

/**
 * Creates a ship
 */
class Ship {
    private int length;
    private int start_x;
    private int start_y;
    private int end_x;
    private int end_y;
    int shipHealth;

    Ship(int startX, int startY, int endX,
         int endY) {
        length =
                1 + (int) Math.sqrt(((endX - startX) * (endX - startX)) + ((endY - startY) * (endY - startY)));
         this.start_x = startX;
        this.start_y = startY;
        this.end_x = endX;
        this.end_y = endY;
        this.shipHealth = length;
    }

    void decrementShipHealth() {
        this.shipHealth--;
    }

    boolean shipIsHit() {
        return this.shipHealth == 0;
    }

    boolean checkShipStatus(int x, int y) {
        if (x >= start_x && x <= end_x && y >= start_y && y <= end_y) {
            decrementShipHealth();
            return true;
        }
        return false;
    }
}