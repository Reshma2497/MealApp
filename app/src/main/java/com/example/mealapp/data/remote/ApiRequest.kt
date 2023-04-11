package com.example.mealapp.data.remote


import com.example.mealapp.data.model.Area.AreasModelModel
import com.example.mealapp.data.model.Ingredients.IngredientsModel
import com.example.mealapp.data.model.categories.CategoriesModel
import com.example.mealapp.data.model.meals.MealsModelModel
import com.example.mealapp.data.model.meals.details.MealDetailsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {

   @GET(ApiDetails.CATEGORIES)
   suspend fun getCategories(): CategoriesModel

   @GET(ApiDetails.MEAL)
   suspend fun getMealByCategory(@Query("c") category: String): MealsModelModel

   @GET(ApiDetails.MEALSDETAILS)
   suspend fun getMealDetails(@Query("i") mealId:String): MealDetailsModel

   @GET(ApiDetails.AREA)
   suspend fun getArea(): AreasModelModel

   @GET(ApiDetails.MEAL)
   suspend fun getMealByArea(@Query("a") area:String): MealsModelModel

   @GET(ApiDetails.INGREDIENTS)
   suspend fun getIngredients(): IngredientsModel

   @GET(ApiDetails.MEAL)
   suspend fun getMealByIngredients(@Query("i") ingredients: String):MealsModelModel

}