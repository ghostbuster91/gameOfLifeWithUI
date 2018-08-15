package com.kasper.game.smartPoint;

import com.kasper.game.ui.GamePanel;

import java.awt.*;


public class SmartPointFactory {
    private GamePanel gamePanel;

    public SmartPointFactory(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public SmartPoint fromMouse(Point point) {
        return new SmartPoint(point, gamePanel);
    }

    public SmartPoint fromGame(Point point) {
        return new SmartPoint(point.x * gamePanel.getBlockSize() + gamePanel.getOffset().x, point.y * gamePanel.getBlockSize() + gamePanel.getOffset().y, gamePanel);
    }

    SmartPoint fromPanel(Point point) {
        SmartPoint sp = new SmartPoint(point, true, gamePanel);
        return sp;
    }
}
