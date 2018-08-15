package com.kasper.game.ui;

import com.kasper.game.IGameUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTask
        implements ActionListener {
    private IGameUI game;
    private JPanel pannel;

    GameTask(IGameUI game, JPanel pannel) {
        this.game = game;
        this.pannel = pannel;
    }

    public void actionPerformed(ActionEvent e) {
        game.tic();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                pannel.repaint();
            }
        });
    }
}
