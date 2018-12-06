package com.example.abiel.p2mvc;

public class Student {
    private int mNoControl;
    private String mName;
    private int mScore;

    public Student(int noControl, String name, int score){
        mNoControl = noControl;
        mName = name;
        mScore = score;
    }

    public int getmNoControl() {
        return mNoControl;
    }

    public void setmNoControl(int mNoControl) {
        this.mNoControl = mNoControl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmScore() {
        return mScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }
}
