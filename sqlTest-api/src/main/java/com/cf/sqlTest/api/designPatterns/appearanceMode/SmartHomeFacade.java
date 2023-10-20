package com.cf.sqlTest.api.designPatterns.appearanceMode;

import com.cf.sqlTest.api.designPatterns.appearanceMode.childSystem.AudioSystem;
import com.cf.sqlTest.api.designPatterns.appearanceMode.childSystem.LightSystem;
import com.cf.sqlTest.api.designPatterns.appearanceMode.childSystem.TemperatureSystem;

/**
 * @author: lpy
 * @Date: 2023/10/20
 */
public class SmartHomeFacade {
    private LightSystem lightSystem;
    private AudioSystem audioSystem;
    private TemperatureSystem temperatureSystem;

    public SmartHomeFacade(){
        this.audioSystem = new AudioSystem();
        this.lightSystem = new LightSystem();
        this.temperatureSystem = new TemperatureSystem();
    }

    public void leaveHome(){
        this.audioSystem.stopMusic();
        this.lightSystem.turnOff();
        this.temperatureSystem.setTemperature(30);
        System.out.println("离家模式");
    }

    public void returnHome(){
        this.audioSystem.playMusic();
        this.lightSystem.turnOn();
        this.temperatureSystem.setTemperature(26);
        System.out.println("回家模式");
    }

}
