package com.example.rydobikes.model;

public class ProductOrderModel {

    private String id;
    private String first_name;
    private String last_name;
    private String street_name;
    private String apartment_status;
    private String suburb_value;
    private String post_code;
    private String phone_value;
    private String email_address;
    private String total_price;

    public ProductOrderModel(String id, String first_name, String last_name, String street_name, String apartment_status, String suburb_value, String post_code, String phone_value, String email_address, String total_price) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.street_name = street_name;
        this.apartment_status = apartment_status;
        this.suburb_value = suburb_value;
        this.post_code = post_code;
        this.phone_value = phone_value;
        this.email_address = email_address;
        this.total_price = total_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getApartment_status() {
        return apartment_status;
    }

    public void setApartment_status(String apartment_status) {
        this.apartment_status = apartment_status;
    }

    public String getSuburb_value() {
        return suburb_value;
    }

    public void setSuburb_value(String suburb_value) {
        this.suburb_value = suburb_value;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getPhone_value() {
        return phone_value;
    }

    public void setPhone_value(String phone_value) {
        this.phone_value = phone_value;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "ProductOrderModel{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", street_name='" + street_name + '\'' +
                ", apartment_status='" + apartment_status + '\'' +
                ", suburb_value='" + suburb_value + '\'' +
                ", post_code='" + post_code + '\'' +
                ", phone_value='" + phone_value + '\'' +
                ", email_address='" + email_address + '\'' +
                ", total_price='" + total_price + '\'' +
                '}';
    }
}
