package com.example.mealapp.data.model.Area


import com.google.gson.annotations.SerializedName

data class AreasModelModel(
    @SerializedName("meals")
    val meals: List<MealModel?>? = listOf()
)