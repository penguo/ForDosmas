package com.pepg.fordosmas;

/**
 * Created by pengu on 2017-11-30.
 */

public class Item {
    String name;
    int price;

    public Item(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
