package com.example.mealapp.data.model.Ingredients


import com.google.gson.annotations.SerializedName

data class IngredientsModel(
    @SerializedName("meals")
    val meals: List<MealModel?>? = listOf()
)