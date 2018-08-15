package com.kasper.game;

public interface IGameUI {
    Iterable<ILifeUI> getLives();

    boolean addLives(ILifeUI... paramVarArgs);

    void tic();

    boolean deleteLife(Life paramLife);

    void clear();
}
