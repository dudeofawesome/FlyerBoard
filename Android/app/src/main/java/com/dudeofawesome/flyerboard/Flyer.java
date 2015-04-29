package com.dudeofawesome.flyerboard;

/**
 * Created by dudeofawesome on 3/29/15.
 */
public class Flyer {
    public static enum CATEGORY {
        technology,
        art,
        science,
        music,
        business,
        literature,
        hobby
    }

    public String title;
    public String group;
    public String date;
    public CATEGORY category;
}
