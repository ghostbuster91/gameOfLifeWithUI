package com.kasper.game.ui;

import java.awt.event.KeyEvent;

public class KeyboardHandler implements java.awt.event.KeyListener {
    private GamePanel gamePanel;

    KeyboardHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == 107) {
            gamePanel.increaseSpeed();
        }

        if (key == 109) {
            gamePanel.decreaseSpeed();
        }
    }

    public void keyReleased(KeyEvent e) {
    }
}
