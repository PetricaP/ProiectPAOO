package paoo.game;

import paoo.core.Vector2D;

import java.util.ArrayList;

public class SingleFireWeapon extends Weapon {
    SingleFireWeapon(String owner) {
        super(owner);

        if(offsets == null) {
            offsets = new Vector2D[4];
            offsets[Direction.UP] = new Vector2D(0, -22);
            offsets[Direction.DOWN] = new Vector2D(-2, 7);
            offsets[Direction.RIGHT] = new Vector2D(7, 0);
            offsets[Direction.LEFT] = new Vector2D(-10, 0);
        }

        ChangeOffset();
    }

    @Override
    public void setLookingDirection(int direction) {
        super.setLookingDirection(direction);

        ChangeOffset();
    }

    @Override
    ArrayList<Bullet> fire() {
        ArrayList<Bullet> bullets = new ArrayList<>();
        bullets.add(new FireBall(new Vector2D(getPosition()).add(offset), getLookingDirection(),
                getOwner() + "FireBullet"));
        return bullets;
    }

    private void ChangeOffset() {
        offset = offsets[getLookingDirection()];
    }

    private static Vector2D[] offsets;
    private Vector2D offset;
}
