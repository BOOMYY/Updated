/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Boomy
 */
public class ItemList {

    public void setDate(String date) {
        this.date = date;
    }
    private String item_id;
    private String name;
    private String item;
    private String model;
    private String price;
    private String date;

    public ItemList(String item_id, String name, String item, String model, String price, String date) {
        this.item_id = item_id;
        this.name = name;
        this.item = item;
        this.model = model;
        this.price = price;
        this.date = date;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

}
