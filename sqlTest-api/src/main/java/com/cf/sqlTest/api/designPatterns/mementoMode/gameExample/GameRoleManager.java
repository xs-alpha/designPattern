package com.cf.sqlTest.api.designPatterns.mementoMode.gameExample;

/**
 * @author: lpy
 * @Date: 2023/10/27
 */
public class GameRoleManager {
    private GameRoleMemento gameRoleMemento;

    public void addMemento(GameRoleMemento gm){
        this.gameRoleMemento = gm;
    }
    public GameRoleMemento getGameRoleMemento(){
        return gameRoleMemento;
    }
}
