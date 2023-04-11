package com.example.mealapp.data.model.meals.details


import com.google.gson.annotations.SerializedName

data class MealDetailsModel(
    @SerializedName("meals")
    val meals: List<MealModel?>? = listOf()
)