package com.example.anish.firebasedemo.fireBaseTuts.model;

/**
 * Created by anish on 24-08-2017.
 */

public class Student {
    Result result;
    Info info;

    public Student(Result result, Info info) {
        this.result = result;
        this.info = info;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
