package com.example.rydobikes.model;

public class ProductCartModel {

    private String id;
    private String product_id;
    private String product_name;
    private String product_price;
    private String product_color;
    private String product_quantity;
    private String product_view_short_des;
    private String product_view_des;
    private String product_view_image;
    private String product_order_id;

    public ProductCartModel(String id, String product_id, String product_name, String product_price, String product_color, String product_quantity, String product_view_short_des, String product_view_des, String product_view_image, String product_order_id) {
        this.id = id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_color = product_color;
        this.product_quantity = product_quantity;
        this.product_view_short_des = product_view_short_des;
        this.product_view_des = product_view_des;
        this.product_view_image = product_view_image;
        this.product_order_id = product_order_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_color() {
        return product_color;
    }

    public void setProduct_color(String product_color) {
        this.product_color = product_color;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getProduct_view_short_des() {
        return product_view_short_des;
    }

    public void setProduct_view_short_des(String product_view_short_des) {
        this.product_view_short_des = product_view_short_des;
    }

    public String getProduct_view_des() {
        return product_view_des;
    }

    public void setProduct_view_des(String product_view_des) {
        this.product_view_des = product_view_des;
    }

    public String getProduct_view_image() {
        return product_view_image;
    }

    public void setProduct_view_image(String product_view_image) {
        this.product_view_image = product_view_image;
    }

    public String getProduct_order_id() {
        return product_order_id;
    }

    public void setProduct_order_id(String product_order_id) {
        this.product_order_id = product_order_id;
    }

    @Override
    public String toString() {
        return "ProductCartModel{" +
                "id='" + id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", product_name='" + product_name + '\'' +
                ", product_price='" + product_price + '\'' +
                ", product_color='" + product_color + '\'' +
                ", product_quantity='" + product_quantity + '\'' +
                ", product_view_short_des='" + product_view_short_des + '\'' +
                ", product_view_des='" + product_view_des + '\'' +
                ", product_view_image='" + product_view_image + '\'' +
                ", product_order_id='" + product_order_id + '\'' +
                '}';
    }
}
