package com.kasper.game.ui;

import com.kasper.game.Game;
import com.kasper.game.IGameUI;

import javax.swing.*;

class MainFrame extends JFrame {
    private static final long serialVersionUID = -463839135678825094L;

    MainFrame() {
        super("Game Of Life");

        IGameUI game = new Game();

        JPanel panel = new GamePanel(game);

        add(panel);

        pack();
        setDefaultCloseOperation(3);
        setVisible(true);
    }
}
