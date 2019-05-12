package paoo.game;

import paoo.core.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

class TankFactory {
    static Tank create(String owner, Tank.Type type) {
        String filePath = "res/images/tanks.png";
        Rectangle rect;

        Weapon weapon;
        switch(type) {
            case PLAYER_1:
                rect = new Rectangle(0, 0, 150, 200);
                weapon = new SingleSimpleWeapon(owner);
                break;
            case PLAYER_2:
                rect = new Rectangle(150, 0, 150, 200);
                weapon = new DoubleSimpleWeapon(owner);
                break;
            case PLAYER_3:
                rect = new Rectangle(300, 0, 150, 200);
                weapon = new SingleFireWeapon(owner);
                break;
            case ENEMY_BLUE_1:
                rect = new Rectangle(0, 200, 150, 165);
                weapon = new DoubleSimpleWeapon(owner);
                break;
            case ENEMY_BLUE_2:
                rect = new Rectangle(150, 200, 150, 165);
                weapon = new SingleSimpleWeapon(owner);
                break;
            case ENEMY_BLUE_3:
                rect = new Rectangle(300, 200, 150, 165);
                weapon = new SingleSimpleWeapon(owner);
                break;
            case ENEMY_RED_1:
                rect = new Rectangle(0, 365, 140, 180);
                weapon = new SingleFireWeapon(owner);
                break;
            case ENEMY_RED_2:
                rect = new Rectangle(140, 365, 140, 180);
                weapon = new SingleFireWeapon(owner);
                break;
            case ENEMY_RED_3:
                rect = new Rectangle(280, 365, 140, 180);
                weapon = new DoubleFireWeapon(owner);
                break;
            default:
            return null;
        }

        try {
            BufferedImage tankImage = ImageLoader.getInstance().loadImage(filePath)
                    .getSubimage(rect.x, rect.y, rect.width, rect.height);
            return new Tank(tankImage, weapon);
        } catch(IOException e) {
            System.err.println("Couldn't load resource " + filePath);
            return null;
        }
    }
}
