package dev.bkrk;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KL extends KeyAdapter implements KeyListener {
    // KL means key listener.
    private boolean[] keyPressed = new boolean[128];
    // keyPressed: This array keeps track of whether each key has been pressed.
    // The length of the array is set to 128, and usually common keyboard keys fall within this range.

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keyPressed[keyEvent.getKeyCode()] = true;
        // keyEvent.getKeyCode(): Gets the code (keyCode) of the key pressed via keyEvent object.
        // keyPressed[keyEvent.getKeyCode()] = true;: sets the corresponding keyCode in the keyPressed array to true,
        // that is, it stores the information that the key is pressed.
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keyPressed[keyEvent.getKeyCode()] = false;
        // keyEvent.getKeyCode(): Gets the code of the released key.
        // keyPressed[keyEvent.getKeyCode()] = false;: makes the corresponding keyCode in the keyPressed array false,
        // that is, it stores the information that the key is no longer pressed.
    }

    public boolean isKeyPressed(int keyCode) {
        return keyPressed[keyCode];
        // This method is used to check if any key is currently pressed.
        //The expression key Pressed[keyCode] returns true or false for the key specified by keyCode.
        // Thus, it is easy to check whether a key is pressed or not in the game or application.
    }

}
