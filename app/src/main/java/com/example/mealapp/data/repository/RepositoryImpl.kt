package com.example.mealapp.data.repository

import com.example.mealapp.data.model.Area.AreasModelModel
import com.example.mealapp.data.model.Ingredients.IngredientsModel
import com.example.mealapp.data.model.categories.CategoriesModel
import com.example.mealapp.data.model.meals.MealsModelModel
import com.example.mealapp.data.model.meals.details.MealDetailsModel
import com.example.mealapp.data.remote.ApiRequest
import retrofit2.http.Query
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiRequest: ApiRequest
) : Repository{
    override suspend fun getCategories(): CategoriesModel =apiRequest.getCategories()
    override suspend fun getMealByCategory( category: String): MealsModelModel =apiRequest.getMealByCategory(category)
    override suspend fun getMealDetails(mealId:String): MealDetailsModel= apiRequest.getMealDetails(mealId)
    override suspend fun getArea(): AreasModelModel =apiRequest.getArea()

    override suspend fun getMealByArea( area: String): MealsModelModel =apiRequest.getMealByArea(area)

    override suspend fun getIngredients(): IngredientsModel =apiRequest.getIngredients()

    override suspend fun getMealByIngredients( ingredients: String): MealsModelModel =apiRequest.getMealByIngredients(ingredients)




}