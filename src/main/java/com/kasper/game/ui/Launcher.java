package com.kasper.game.ui;

import javax.swing.*;
import java.awt.*;

public class Launcher {
    private static final String instructions = "PPM -> dodaj komórke\n LPM -> usuń komórkę\n SPM -> wyczysc plansze\n NUMPAD: +/- regulacja tempa\n Wyjdz kursorem po za plansze aby rozpocząć symulacje\n Przytrzymaj srodkowy przycisk myszy i przesun aby przesunac plansze";

    public Launcher() {
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JOptionPane.showMessageDialog(null, instructions);
                new MainFrame();
            }
        });
    }
}
