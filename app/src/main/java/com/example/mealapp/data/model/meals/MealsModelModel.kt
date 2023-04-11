package com.example.mealapp.data.model.meals


import com.google.gson.annotations.SerializedName

data class MealsModelModel(
    @SerializedName("meals")
    val meals: List<MealModel?>? = listOf()
)