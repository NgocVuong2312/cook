package com.example.recipies;
//lop khai bao cac bien cua app
public class Item {
    private String Name,img_url;
    private String recipe;

    public Item(String name,  String img_url, String recipe) {
        this.Name = name;
        this.img_url = img_url;
        this.recipe = recipe;
        //this.food_url = food_url;
    }
    public String getName() {
        return Name;
    }
    public String getRecipe() {
        return recipe;
    }
    public String getImg_url() {
        return img_url;
    }
    //public String getFood_url() {
    //    return food_url;
    //}
}
