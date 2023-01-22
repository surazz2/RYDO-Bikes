package com.example.rydobikes.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductModel implements Parcelable {

    private String id;
    private String title;
    private String shortDes;
    private String description;
    private String color;
    private String image;
    private String quantity;
    private String price;

    public ProductModel(String id, String title, String shortDes, String description, String color, String image, String quantity, String price) {
        this.id = id;
        this.title = title;
        this.shortDes = shortDes;
        this.description = description;
        this.color = color;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
    }

    protected ProductModel(Parcel in) {
        id = in.readString();
        title = in.readString();
        shortDes = in.readString();
        description = in.readString();
        color = in.readString();
        image = in.readString();
        quantity = in.readString();
        price = in.readString();
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", shortDes='" + shortDes + '\'' +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", image='" + image + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(shortDes);
        parcel.writeString(description);
        parcel.writeString(color);
        parcel.writeString(image);
        parcel.writeString(quantity);
        parcel.writeString(price);
    }
}
