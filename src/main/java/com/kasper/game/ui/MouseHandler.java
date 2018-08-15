package com.kasper.game.ui;

import java.awt.*;
import java.awt.event.*;

public class MouseHandler
        implements MouseMotionListener, MouseListener, MouseWheelListener {
    private GamePanel panel;
    private int currentMouseButton = -1;
    private Point startDragging = null;
    private Point endDragging = null;

    MouseHandler(GamePanel gamePanel) {
        panel = gamePanel;
    }

    public void mouseClicked(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                panel.addLife(e.getPoint());
                break;
            case MouseEvent.BUTTON2:
                panel.clearGame();
                break;
            case MouseEvent.BUTTON3:
                panel.deleteLife(e.getPoint());
                break;
        }
    }

    public void mousePressed(MouseEvent e) {
        currentMouseButton = e.getButton();
        startDragging = e.getPoint();
    }

    public void mouseReleased(MouseEvent e) {
        currentMouseButton = -1;
        panel.submitOffset();
        startDragging = null;
        endDragging = null;
    }

    public void mouseEntered(MouseEvent e) {
        if (panel.isTimerRunning()) {
            panel.stopTimer();
            panel.reinitTimer();
        }
    }

    public void mouseExited(MouseEvent e) {
        if (!panel.isTimerRunning()) {
            panel.reinitTimer();
            panel.startTimer();
        }
    }

    public void mouseDragged(MouseEvent e) {
        switch (currentMouseButton) {
            case 1:
                panel.addLife(e.getPoint());
                break;
            case 3:
                panel.deleteLife(e.getPoint());
                break;
            case 2:
                endDragging = e.getPoint();
                int xVal = endDragging.x - startDragging.x;
                int yVal = endDragging.y - startDragging.y;
                panel.moveBoard(xVal, yVal);
                break;
        }

    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseWheelMoved(MouseWheelEvent event) {
        int steps = event.getWheelRotation();

        if (event.getScrollType() == 0) {
            panel.changeBlockSize(steps);
        }
    }
}
