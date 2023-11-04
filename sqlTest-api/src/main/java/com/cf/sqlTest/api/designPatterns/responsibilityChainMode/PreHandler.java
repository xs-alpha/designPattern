package com.cf.sqlTest.api.designPatterns.responsibilityChainMode;

/**
 * @author: lpy
 * @Date: 2023/11/04
 */
public class PreHandler extends Handler{
    public PreHandler(String name) {
        super(name);
    }

    @Override
    public void deal(Request req) {
        if (req.getLevel()>5){
            System.out.println(name+"无权处理");
            if (handler!=null){
                this.handler.deal(req);
            }
        }else{
            System.out.println(name+"处理"+req.toString());
            if (handler!=null){
                this.handler.deal(req);
            }
        }
    }
}
