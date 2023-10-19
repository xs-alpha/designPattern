package com.cf.sqlTest.api.designPatterns.prototypeMode;

import lombok.Data;

/**
 * @author: lpy
 * @Date: 2023/10/18
 */
@Data
public class Resume implements Cloneable{
    public String name;
    public Integer age;
    public String address;
    public String company;
    public String birthDay;

    /** 项目经历，深复制测试*/
    private ProjectExperience pe;

    public void setPe(String projectTitle, String circle){
        this.pe.setCircle(circle);
        this.pe.setProjectTitle(projectTitle);
    }

    public Resume(String name,int age){
        this.name =name;
        this.age = age;
        this.pe = new ProjectExperience();
    }


    public void display() {
        System.out.println("Resume{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", company='" + company + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", projectTitle='"+this.pe.getProjectTitle()+'\''+
                ", circle='"+this.pe.getCircle()+'\''+
                '}');
    }

    public void setPersonalInfo(String address, String company, String birthDay){
        this.address=address;
        this.company=company;
        this.birthDay=birthDay;
    }

    @Override
    public Resume clone(){
        Resume re = null;
        try {
            re = (Resume)super.clone();
            re.pe = this.pe.clone();
        }catch (CloneNotSupportedException ex){
            System.out.println("clone失败"+ex.getMessage());
        }
        return  re;
    }
}
