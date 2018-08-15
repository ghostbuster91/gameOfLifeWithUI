package com.kasper.game;

import java.awt.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Life implements ILifeUI {
    private final int x;
    private final int y;

    private Life(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Life(Point point) {
        this(point.x, point.y);
    }


    public String toString() {
        return "X: " + x + " Y: " + y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    Set<ILifeUI> getAllPossibleNeighboors() {
        Set<ILifeUI> possible = new HashSet<>();
        possible.add(new Life(x - 1, y - 1));
        possible.add(new Life(x - 1, y));
        possible.add(new Life(x - 1, y + 1));

        possible.add(new Life(x, y - 1));
        possible.add(new Life(x, y + 1));

        possible.add(new Life(x + 1, y - 1));
        possible.add(new Life(x + 1, y));
        possible.add(new Life(x + 1, y + 1));

        return possible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Life life = (Life) o;
        return x == life.x &&
                y == life.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
