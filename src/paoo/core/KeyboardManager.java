package paoo.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener {
    public boolean isPressed(char c) {
        return keysPressed[c];
    }

    public static KeyboardManager getInstance() {
        if(instance == null) {
            instance = new KeyboardManager();
        }
        return instance;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() < Character.MAX_VALUE) {
            keysPressed[e.getKeyChar()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() < Character.MAX_VALUE) {
			keysPressed[e.getKeyChar()] = false;
		}
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private KeyboardManager() {}

    private boolean[] keysPressed = new boolean[Character.MAX_VALUE];
    private static KeyboardManager instance = null;
}
