package com.cf.sqlTest.api.designPatterns.mementoMode.gameExample;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author: lpy
 * @Date: 2023/10/27
 */
@NoArgsConstructor
@AllArgsConstructor
public class GameRole {
    private String name;
    private String bloodVolume;
    private String schedule;

    public GameRoleMemento saveToMemento(){
        return new GameRoleMemento(name,bloodVolume,schedule);
    }

    public void undoFromMemento(GameRoleMemento gm){
        this.name = gm.getName();
        this.bloodVolume = gm.getBloodVolume();
        this.schedule = gm.getSchedule();
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBloodVolume() {
        return bloodVolume;
    }
    public void setBloodVolume(String bloodVolume) {
        this.bloodVolume = bloodVolume;
    }
    public String getSchedule() {
        return schedule;
    }
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
