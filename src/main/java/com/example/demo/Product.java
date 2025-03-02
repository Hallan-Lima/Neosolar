package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    @JsonProperty("Categoria")
    private String category;

    @JsonProperty("Id")
    private int id;

    @JsonProperty("Potencia em W")
    private int power;

    @JsonProperty("Produto")
    private String product;

    private int qt;

    // Getters e Setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQt() {
        return qt;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }


    // Método toString para depuração
    @Override
    public String toString() {
        return "Product{" +
                "category='" + category + '\'' +
                ", id=" + id +
                ", power=" + power +
                ", product='" + product + '\'' +
                ", qt=" + qt +
                '}';
    }
}