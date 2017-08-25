package com.example.anish.firebasedemo.fireBaseTuts.model;

/**
 * Created by anish on 24-08-2017.
 */

public class Info {
    String RollNo;
    String Name;
    String infoId;

    public Info(String rollNo, String name, String infoId) {
        RollNo = rollNo;
        Name = name;
        this.infoId = infoId;
    }

    public String getRollNo() {
        return RollNo;
    }

    public void setRollNo(String rollNo) {
        RollNo = rollNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
