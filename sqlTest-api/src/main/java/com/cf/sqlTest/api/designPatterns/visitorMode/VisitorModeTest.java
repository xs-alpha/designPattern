package com.cf.sqlTest.api.designPatterns.visitorMode;

/**
 * @author: lpy
 * @Date: 2023/11/07
 */
public class VisitorModeTest {
    public static void main(String[] args) {
        ObjectStructure obs = new ObjectStructure();
        obs.add(new Man());
        obs.add(new Women());

        obs.visitor(new Success());
        obs.visitor(new Failure());
    }
}
