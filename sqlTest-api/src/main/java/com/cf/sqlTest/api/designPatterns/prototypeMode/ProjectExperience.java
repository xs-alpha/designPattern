package com.cf.sqlTest.api.designPatterns.prototypeMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lpy
 * @Date: 2023/10/19
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProjectExperience implements Cloneable{
    private String projectTitle;
    private String circle;

    @Override
    public ProjectExperience clone(){
        ProjectExperience pe = null;
        try {
            pe = (ProjectExperience)super.clone();
        }catch (CloneNotSupportedException ex){
            System.out.println("clone失败"+ex.getMessage());
        }
        return  pe;
    }
}
