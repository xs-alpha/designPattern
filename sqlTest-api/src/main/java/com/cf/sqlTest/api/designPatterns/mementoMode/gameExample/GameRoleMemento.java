package com.cf.sqlTest.api.designPatterns.mementoMode.gameExample;

import lombok.AllArgsConstructor;

/**
 * @author: lpy
 * @Date: 2023/10/27
 * @desc: 注意，他是一个窄接口，不需要set方法，他是快照
 */
@AllArgsConstructor
public class GameRoleMemento {
    private String name;
    private String bloodVolume;
    private String schedule;



    public String getName() {
        return name;
    }

    public String getBloodVolume() {
        return bloodVolume;
    }

    public String getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return "GameRoleMemento{" +
                "name='" + name + '\'' +
                ", bloodVolume='" + bloodVolume + '\'' +
                ", schedule='" + schedule + '\'' +
                '}';
    }
}
