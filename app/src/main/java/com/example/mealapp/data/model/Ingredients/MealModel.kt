package com.example.mealapp.data.model.Ingredients


import com.google.gson.annotations.SerializedName

data class MealModel(
    @SerializedName("idIngredient")
    val idIngredient: String? = "",
    @SerializedName("strDescription")
    val strDescription: String? = "",
    @SerializedName("strIngredient")
    val strIngredient: String? = "",
    @SerializedName("strType")
    val strType: String? = ""
)