package org.whitetech.nrs.numeric_road_signs;

public class LangItem {

    int id ;
    String name , enName , arName;
    public LangItem(int id , String name , String enName , String arName) {
        this.id = id;
        this.name = name;
        this.enName = enName;
        this.arName  = arName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEnName() {
        return enName;
    }

    public String getArName() {
        return arName;
    }
}
