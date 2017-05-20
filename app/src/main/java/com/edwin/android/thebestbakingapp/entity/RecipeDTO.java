package com.edwin.android.thebestbakingapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Edwin Ramirez Ventur on 5/20/2017.
 */

public class RecipeDTO implements Parcelable {

    @com.google.gson.annotations.SerializedName("id")
    private int id;
    @com.google.gson.annotations.SerializedName("name")
    private String name;
    @com.google.gson.annotations.SerializedName("ingredients")
    private List<IngredientDTO> ingredients;
    @com.google.gson.annotations.SerializedName("steps")
    private List<StepsDTO> steps;
    @com.google.gson.annotations.SerializedName("servings")
    private int servings;
    @com.google.gson.annotations.SerializedName("image")
    private String image;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public List<StepsDTO> getSteps() {
        return steps;
    }

    public void setSteps(List<StepsDTO> steps) {
        this.steps = steps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeDTO recipe = (RecipeDTO) o;

        return id == recipe.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "RecipeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                ", servings=" + servings +
                ", image='" + image + '\'' +
                '}';
    }

    protected RecipeDTO(Parcel in) {
        id = in.readInt();
        name = in.readString();
        steps = in.createTypedArrayList(StepsDTO.CREATOR);
        servings = in.readInt();
        image = in.readString();
    }

    public static final Creator<RecipeDTO> CREATOR = new Creator<RecipeDTO>() {
        @Override
        public RecipeDTO createFromParcel(Parcel in) {
            return new RecipeDTO(in);
        }

        @Override
        public RecipeDTO[] newArray(int size) {
            return new RecipeDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedList(steps);
        dest.writeInt(servings);
        dest.writeString(image);
    }
}
