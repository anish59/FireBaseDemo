package com.example.anish.firebasedemo.fireBaseTuts.model;

/**
 * Created by anish on 24-08-2017.
 */

public class Result {
    String M1;
    String M2;
    String resultId;

    public Result(String m1, String m2, String resultId) {
        M1 = m1;
        M2 = m2;
        this.resultId = resultId;
    }

    public String getM1() {
        return M1;
    }

    public void setM1(String m1) {
        M1 = m1;
    }

    public String getM2() {
        return M2;
    }

    public void setM2(String m2) {
        M2 = m2;
    }
}
