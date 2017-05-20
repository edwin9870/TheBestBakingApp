package com.edwin.android.thebestbakingapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class IngredientDTO implements Parcelable {

    @com.google.gson.annotations.SerializedName("quantity")
    private double quantity;
    @com.google.gson.annotations.SerializedName("measure")
    private String measure;
    @com.google.gson.annotations.SerializedName("ingredient")
    private String ingredient;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "IngredientDTO{" +
                "quantity=" + quantity +
                ", measure='" + measure + '\'' +
                ", ingredient='" + ingredient + '\'' +
                '}';
    }


    protected IngredientDTO(Parcel in) {
        quantity = in.readDouble();
        measure = in.readString();
        ingredient = in.readString();
    }

    public static final Creator<IngredientDTO> CREATOR = new Creator<IngredientDTO>() {
        @Override
        public IngredientDTO createFromParcel(Parcel in) {
            return new IngredientDTO(in);
        }

        @Override
        public IngredientDTO[] newArray(int size) {
            return new IngredientDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(quantity);
        dest.writeString(measure);
        dest.writeString(ingredient);
    }
}
