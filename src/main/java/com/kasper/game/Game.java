package com.kasper.game;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Game implements IGameUI {
    public Game() {
    }

    private Set<ILifeUI> lives = new HashSet<>();

    public Iterable<ILifeUI> getLives() {
        return lives;
    }

    public boolean addLives(ILifeUI... lives) {
        return this.lives.addAll(Arrays.asList(lives));
    }

    public void tic() {
        Iterable<ILifeUI> survived = getSurviving();
        Iterable<ILifeUI> emerged = getEmerging();
        lives = Sets.newHashSet(Iterables.concat(survived, emerged));
    }

    private Iterable<ILifeUI> getEmerging() {
        Iterable<Iterable<ILifeUI>> transform = Iterables.transform(lives, new Function<ILifeUI, Iterable<ILifeUI>>() {
            public Iterable<ILifeUI> apply(ILifeUI life) {
                return ((Life) life).getAllPossibleNeighboors();
            }
        });
        return Iterables.filter(Iterables.concat(transform), new Predicate<ILifeUI>() {
            public boolean apply(ILifeUI life) {
                return Game.this.shouldEmerge((Life) life);
            }
        });
    }

    private Iterable<ILifeUI> getSurviving() {
        return Iterables.filter(lives, new Predicate<ILifeUI>() {
            public boolean apply(ILifeUI life) {
                return shouldSurvive((Life) life);
            }
        });
    }

    private boolean shouldSurvive(Life life) {
        int count = getAllAliveNeighboorsCount(life);
        return (count == 2) || (count == 3);
    }

    private boolean shouldEmerge(Life life) {
        if (lives.contains(life)) {
            return false;
        }
        return getAllAliveNeighboorsCount(life) == 3;
    }

    private int getAllAliveNeighboorsCount(ILifeUI life) {
        return Sets.filter(((Life) life).getAllPossibleNeighboors(), new Predicate<ILifeUI>() {
            public boolean apply(ILifeUI arg0) {
                return lives.contains(arg0);
            }
        }).size();
    }

    public boolean isAlive(Life life) {
        return lives.contains(life);
    }

    public boolean deleteLife(Life life) {
        return lives.remove(life);
    }


    public void clear() {
        lives.clear();
    }
}
