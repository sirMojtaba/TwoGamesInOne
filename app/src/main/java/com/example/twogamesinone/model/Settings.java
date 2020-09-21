package com.example.twogamesinone.model;

public class Settings {

    private String[] mNames = new String[2];
    private boolean mShowPoints;
    private static Settings sSettings;

    public static Settings getInstance() {
        if (sSettings == null)
            sSettings = new Settings();

        return sSettings;
    }

    private Settings() {
        mShowPoints = false;
        mNames[0] = "player 1";
        mNames[1] = "player 2";
    }

    public String[] getNames() {
        return mNames;
    }

    public void setNames(String[] mNames) {
        this.mNames = mNames;
    }

    public boolean getShowPoints() {
        return mShowPoints;
    }

    public void setShowPoints(boolean mShowPoints) {
        this.mShowPoints = mShowPoints;
    }
}
