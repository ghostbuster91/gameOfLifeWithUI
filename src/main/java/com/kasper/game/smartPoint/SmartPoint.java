package com.kasper.game.smartPoint;

import com.kasper.game.ui.GamePanel;

import java.awt.*;

public class SmartPoint
        extends Point {
    private static final long serialVersionUID = -5938895896026224991L;
    private GamePanel gamePanel;

    SmartPoint(Point point, boolean withOffset, GamePanel gamePanel) {
        super(point);
        this.gamePanel = gamePanel;
        if (withOffset) {
            addOffset();
        }
    }

    SmartPoint(Point point, GamePanel gamePanel) {
        super(point);
        this.gamePanel = gamePanel;
    }

    SmartPoint(int x, int y, GamePanel gamePanel) {
        super(x, y);
        this.gamePanel = gamePanel;
    }

    private void addOffset() {
        x -= gamePanel.getOffset().x;
        y -= gamePanel.getOffset().y;
    }

    public Point getGamePoint() throws IllegalArgumentException {
        SmartPointFactory spf = new SmartPointFactory(gamePanel);
        SmartPoint p = spf.fromPanel(getPanelPoint());
        p.convertToLifeCoords();
        return p;
    }

    private Point getPanelPoint() throws IllegalArgumentException {
        return findNearestLefCorner();
    }

    private void convertToLifeCoords() {
        setLocation(x / gamePanel.getBlockSize(), y / gamePanel.getBlockSize());
    }

    private Point findNearestLefCorner() throws IllegalArgumentException {
        return new Point(findNearestBlockCornerFor(getX(), gamePanel.getWidth(), gamePanel.getOffset().x), findNearestBlockCornerFor(getY(), gamePanel.getHeight(), gamePanel.getOffset().y));
    }

    private int findNearestBlockCornerFor(double point, int margin, int offset)
            throws IllegalArgumentException {
        if (point >= margin) {
            throw new IllegalArgumentException();
        }
        int start = 0;
        int corner = 0;
        for (int i = 1; start < margin; i++) {
            start = gamePanel.getBlockSize() * i + offset % gamePanel.getBlockSize();
            if (start > point) {
                break;
            }
            corner = start;
        }
        return corner;
    }


    public int getXINT() {
        return (int) super.getX();
    }

    public int getYINT() {
        return (int) super.getY();
    }
}
