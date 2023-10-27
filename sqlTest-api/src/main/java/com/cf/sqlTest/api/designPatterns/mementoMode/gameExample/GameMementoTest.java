package com.cf.sqlTest.api.designPatterns.mementoMode.gameExample;

/**
 * @author: lpy
 * @Date: 2023/10/27
 */
public class GameMementoTest {
    public static void main(String[] args) {
        GameRoleManager manager = new GameRoleManager();

        GameRole siko = new GameRole("赛克", "100", "第三关");
        GameRoleMemento gameRoleMemento = siko.saveToMemento();
        manager.addMemento(gameRoleMemento);
        System.out.println("第一次："+gameRoleMemento.toString());

        siko.setSchedule("第四关");
        siko.setBloodVolume("90");
        gameRoleMemento = manager.getGameRoleMemento();
        siko.undoFromMemento(gameRoleMemento);
        System.out.println("第二次："+gameRoleMemento.toString());

        siko.setSchedule("第四关");
        siko.setBloodVolume("95");
        gameRoleMemento = siko.saveToMemento();
        manager.addMemento(gameRoleMemento);
        System.out.println("第三次："+gameRoleMemento.toString());

    }
}
