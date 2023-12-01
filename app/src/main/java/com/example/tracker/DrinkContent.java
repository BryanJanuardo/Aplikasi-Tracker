package com.example.tracker;

import java.io.Serializable;

public class DrinkContent extends ItemContent implements Serializable {
    private Double volume;

    @Override
    public String displayJenisItem() {
        return ("Minuman");
    }

    public DrinkContent(String namaItem, Double kaloriItem, Double volume, String jenisItem) {
        super(namaItem, kaloriItem, jenisItem);
        this.volume = volume;
    }
    public DrinkContent(){}

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
}
