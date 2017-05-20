package com.edwin.android.thebestbakingapp.entity;

public class IngredientDTO {

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
}
