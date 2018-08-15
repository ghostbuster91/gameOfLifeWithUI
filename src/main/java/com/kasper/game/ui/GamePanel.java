package com.kasper.game.ui;

import com.kasper.game.IGameUI;
import com.kasper.game.ILifeUI;
import com.kasper.game.Life;
import com.kasper.game.smartPoint.SmartPoint;
import com.kasper.game.smartPoint.SmartPointFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel
        extends JPanel {
    private static final long serialVersionUID = -6084098732950736134L;
    private static final int ticMinValue = 100;
    private int blockSize = 20;
    private Point bufferedOffset = new Point(0, 0);
    private Point offset = new Point(0, 0);
    private static final int ticMinDif = 10;
    private int tickDelay = 500;

    private IGameUI game;

    private Timer timer;

    private Timer toolTipTimer;
    private GameTask timerTask;
    private MouseHandler mouseHandler;
    private SmartPointFactory spFactory;
    private boolean drawTip = false;

    GamePanel(IGameUI game) {
        this.game = game;
        setPreferredSize(new Dimension(400, 400));

        initMouseHandler();

        timerTask = new GameTask(game, this);
        timer = new Timer(tickDelay, timerTask);
        spFactory = new SmartPointFactory(this);

        KeyboardHandler keyHandler = new KeyboardHandler(this);
        setFocusable(true);
        addKeyListener(keyHandler);
    }

    private void initMouseHandler() {
        mouseHandler = new MouseHandler(this);

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        addMouseWheelListener(mouseHandler);
    }

    void reinitTimer() {
        timer = new Timer(tickDelay, timerTask);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        drawLines(g, width, height, false);
        drawLines(g, width, height, true);

        for (ILifeUI life : game.getLives()) {
            paintLife(g, life);
        }

        drawTip(g);
    }

    private void drawTip(Graphics g) {
        int tipWidth = 65;
        g.setColor(Color.RED);
        if (drawTip) {
            g.drawString(tickDelay + " ms", getWidth() - tipWidth, (int) (getHeight() * 0.9D));
        }
    }

    private void drawLines(Graphics g, int width, int height, boolean OX) {
        g.setColor(Color.LIGHT_GRAY);
        int start = 0;
        int margin = OX ? width : height;
        int offset = OX ? bufferedOffset.x : bufferedOffset.y;

        for (int i = 0; start < margin; i++) {
            start = blockSize * i + offset % blockSize;
            if (OX) {
                g.drawLine(start, 0, start, height);
            } else {
                g.drawLine(0, start, width, start);
            }
        }
    }

    private void paintLife(Graphics g, ILifeUI life) {
        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;
        SmartPoint sp = spFactory.fromGame(new Point(life.getX(), life.getY()));
        g2d.fillRect(sp.getXINT(), sp.getYINT(), blockSize, blockSize);
    }

    void addLife(Point point) {
        SmartPoint sp = spFactory.fromMouse(point);
        try {
            if (game.addLives(new Life(sp.getGamePoint()))) {
                repaint();
            }
        } catch (IllegalArgumentException ignored) {
        }
    }

    void deleteLife(Point point) {
        SmartPoint sp = spFactory.fromMouse(point);
        if (game.deleteLife(new Life(sp.getGamePoint()))) {
            repaint();
        }
    }

    void clearGame() {
        game.clear();
        repaint();
    }

    boolean isTimerRunning() {
        return timer.isRunning();
    }

    void startTimer() {
        timer.start();
    }

    void stopTimer() {
        timer.stop();
    }

    void changeBlockSize(int steps) {
        if ((blockSize - steps > 5) && (blockSize - steps < 35)) {
            blockSize -= steps;
            repaint();
        }
    }

    public int getBlockSize() {
        return blockSize;
    }

    public Point getOffset() {
        return bufferedOffset;
    }

    void moveBoard(int xVal, int yVal) {
        bufferedOffset.setLocation(offset.x + xVal, offset.y + yVal);
        repaint();
    }

    void submitOffset() {
        offset.setLocation(bufferedOffset);
    }

    void increaseSpeed() {
        tickDelay += ticMinDif;
        showTicInfo();
    }

    void decreaseSpeed() {
        if (tickDelay > ticMinValue) {
            tickDelay -= ticMinDif;
            showTicInfo();
        }
    }

    private void showTicInfo() {
        drawTip = true;

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawTip = false;
                getToolTipTimer().stop();
                repaint();
            }
        };
        repaint();
        toolTipTimer = new Timer(2000, al);
        toolTipTimer.start();
    }

    private Timer getToolTipTimer() {
        return toolTipTimer;
    }
}
