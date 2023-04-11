package com.example.mealapp.data.repository

import com.example.mealapp.data.model.Area.AreasModelModel
import com.example.mealapp.data.model.Ingredients.IngredientsModel
import com.example.mealapp.data.model.categories.CategoriesModel
import com.example.mealapp.data.model.meals.MealsModelModel
import com.example.mealapp.data.model.meals.details.MealDetailsModel
import retrofit2.http.Query

interface Repository {

    suspend fun getCategories(): CategoriesModel

    suspend fun getMealByCategory(@Query("c") category: String ) : MealsModelModel

    suspend fun getMealDetails(@Query("i") mealId:String): MealDetailsModel

    suspend fun getArea(): AreasModelModel

    suspend fun getMealByArea(@Query("a") area: String ) : MealsModelModel

    suspend fun  getIngredients(): IngredientsModel

    suspend fun getMealByIngredients(@Query("i") ingredients: String ) : MealsModelModel
}