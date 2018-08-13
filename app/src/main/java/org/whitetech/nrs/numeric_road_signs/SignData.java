package org.whitetech.nrs.numeric_road_signs;

public class SignData {
    int id ;
    String text;
    public SignData(int id , String text) {
        this.id = id;
        this.text = text;

    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
