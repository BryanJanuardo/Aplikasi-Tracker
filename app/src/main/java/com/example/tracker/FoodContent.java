package com.example.tracker;

import java.io.Serializable;

public class FoodContent extends ItemContent implements Serializable {
    private Double berat;

    @Override
    public String displayJenisItem() {
        return ("Makanan");
    }

    public FoodContent(String namaItem, Double kaloriItem, Double berat, String jenisItem) {
        super(namaItem, kaloriItem, jenisItem);
        this.berat = berat;
    }

    public FoodContent(){}
    public Double getBerat() {
        return berat;
    }

    public void setBerat(Double berat) {
        this.berat = berat;
    }
}
