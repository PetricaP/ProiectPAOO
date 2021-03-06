package paoo.game.entities.bullets;

import paoo.core.Animation;
import paoo.core.ImageLoader;
import paoo.core.utils.Pair;
import paoo.core.utils.Vector2D;
import paoo.game.Direction;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FireBall extends Bullet {
    public FireBall(Vector2D position, int direction, String tag, int speed) {
        super(position, new Vector2D(20, 7), direction, speed, tag);

        this.direction = direction;

        switch(direction) {
            case Direction.UP:
            case Direction.DOWN:
                float dim1 = getDimensions().y;
                float dim2 = getDimensions().x;
                setDimension(new Vector2D(dim1, dim2));
                break;
        }

        if(spriteSheet == null) {
            try {
                spriteSheet = ImageLoader.getInstance().loadImage("res/images/fireball.png");
            } catch(IOException e) {
                System.err.println("Couldn't load image for fireball");
                System.exit(-1);
            }
        }

        if(animationRects == null) {
            animationRects = new ArrayList<>();
            final int offsetX = 60;
            int startX = 0;
            animationRects.add(new Pair<>(new Vector2D(startX, 0), new Vector2D(offsetX, 18)));
            startX += offsetX;
            animationRects.add(new Pair<>(new Vector2D(startX, 0), new Vector2D(offsetX, 18)));
            startX += offsetX;
            animationRects.add(new Pair<>(new Vector2D(startX, 0), new Vector2D(offsetX, 18)));
            startX += offsetX;
            animationRects.add(new Pair<>(new Vector2D(startX, 0), new Vector2D(offsetX, 18)));
            startX += offsetX;
            animationRects.add(new Pair<>(new Vector2D(startX, 0), new Vector2D(offsetX, 18)));
            startX += offsetX;
            animationRects.add(new Pair<>(new Vector2D(startX, 0), new Vector2D(offsetX, 18)));
            startX += offsetX;
            animationRects.add(new Pair<>(new Vector2D(startX, 0), new Vector2D(offsetX, 18)));
            startX += offsetX;
            animationRects.add(new Pair<>(new Vector2D(startX, 0), new Vector2D(offsetX, 18)));
        }

        animation = new Animation(spriteSheet, FRAME_TIME, animationRects,0, 5, null);

        imageCache = new HashMap<>();

        theta = 0;
        switch(direction) {
            case Direction.UP:
                theta = -90;
                break;
            case Direction.DOWN:
                theta = 90;
                break;
            case Direction.LEFT:
                theta = 180;
                break;
        }
    }

    @Override
    public void draw(Graphics graphics) {
        BufferedImage image = animation.getCurrentImage();
        BufferedImage[] row = imageCache.get(image);
        if(row == null) {
            row = new BufferedImage[4];
            row[direction] = image;
            imageCache.put(image, row);
        } else {
            if(row[direction] == null) {
                Vector2D imageCenter = new Vector2D(image.getWidth(null) / 2,
                        image.getHeight(null) / 2);

                AffineTransformOp imageTransformOperation = new AffineTransformOp(
                        AffineTransform.getRotateInstance(Math.toRadians(theta), imageCenter.x, imageCenter.y),
                        AffineTransformOp.TYPE_BILINEAR);

                row[direction] = imageTransformOperation.filter(image, null);
            }
        }

        graphics.drawImage(row[direction], (int)getPosition().x, (int)getPosition().y,
                           (int)getDimensions().x, (int)getDimensions().y, null);
    }

    @Override
    public int getDamage() {
        return 2;
    }

    @Override
    public void setPosition(Vector2D position) { }

    private static Map<BufferedImage, BufferedImage[]> imageCache;
    private static ArrayList<Pair<Vector2D, Vector2D>> animationRects = null;
    private static final int FRAME_TIME = 100;
    private static BufferedImage spriteSheet = null;

    private int direction;
    private int theta;
    private Animation animation;
}
