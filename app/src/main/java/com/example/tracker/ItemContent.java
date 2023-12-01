package com.example.tracker;


import java.io.Serializable;

public abstract class ItemContent implements Serializable {
    private String namaItem;
    private Double kaloriItem;
    private String jenisItem;

    public String displayNamaItem()
    {
        return (namaItem);
    }

    public String displayKaloriItem()
    {
        return (kaloriItem.toString());
    }

    public String displayJenisItem()
    {
        return ("Tidak Diketahui!");
    }

    public ItemContent(String namaItem, Double kaloriItem, String jenisItem){
        this.namaItem = namaItem;
        this.kaloriItem = kaloriItem;
        this.jenisItem = jenisItem;
    }

    public ItemContent(){}

    public Double getKaloriItem() {
        return kaloriItem;
    }

    public String getNamaItem() {
        return namaItem;
    }

    public void setKaloriItem(Double kaloriItem) {
        this.kaloriItem = kaloriItem;
    }

    public void setNamaItem(String namaItem) {
        this.namaItem = namaItem;
    }
}
