package paoo.core.collisions;

import paoo.core.utils.Vector2D;

public class Collision {
    private Collidable otherObject;

    Collision() {}

    Collision(Collision other) {
        this.otherObject = other.otherObject;
    }

    public Collidable getOtherObject() {
        return otherObject;
    }

    void setOtherObject(Collidable other) {
        otherObject = other;
    }

    public static void resolveCollision(Collidable entity1, Collidable entity2) {
        Collider collider1 = entity1.getCollider();

        float xc = collider1.getCenter().x;
        float yc = collider1.getCenter().y;

        Collider collider2 = entity2.getCollider();

        float x1 = collider2.getPosition().x;
        float y1 = collider2.getPosition().y;

        float x1p = x1 + collider2.getDimensions().x;
        float y1p = y1 + collider2.getDimensions().y;

        float x2 = x1;
        float y2 = y1p;

        float x2p = x1p;
        float y2p = y1;

        float yc1 = y1 - (y1p - y1) * (x1 - xc) / (x1p - x1);
        float yc2 = y2 - (y2p - y2) * (x2 - xc) / (x2p - x2);

        float d1 = yc1 - yc;
        float d2 = yc2 - yc;

        // The coordinates of the corners for the two objects
        x1 = collider1.getPosition().x;
        y1 = collider1.getPosition().y;
        x1p = x1 + collider1.getDimensions().x;
        y1p = y1 + collider1.getDimensions().y;

        x2 = collider2.getPosition().x;
        y2 = collider2.getPosition().y;
        x2p = x2 + collider2.getDimensions().x;
        y2p = y2 + collider2.getDimensions().y;

        Vector2D delta;
        if(d1 >= 0 && d2 <= 0) {
            // Right quadrant (1)
            delta = new Vector2D(x2p - x1, 0);
        } else if(d1 >= 0 && d2 >= 0) {
            // Top quadrant (2)
            delta = new Vector2D(0, y2 - y1p);
        } else if(d1 <= 0 && d2 >= 0) {
            // Left quadrant (3)
            delta = new Vector2D(x2 - x1p, 0);
        } else {
            // Bottom quadrant (4)
            delta = new Vector2D(0, y2p - y1);
        }
        entity1.setPosition(new Vector2D(entity1.getPosition()).add(delta));
    }
}
