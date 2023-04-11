package com.example.mealapp.ui.meals

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapp.data.model.errorhandling.ResultOf
import com.example.mealapp.data.model.meals.MealsModelModel
import com.example.mealapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {
    val meal= MutableLiveData<ResultOf<MealsModelModel>>()

    fun getMealByFilter(filterType: String, filterValue: String){
        viewModelScope.launch {
            try {
                if(filterType=="Category") {
                    val result = repository.getMealByCategory(filterValue)
                    meal.postValue(ResultOf.Success(result))
                }
                else if(filterType=="Area")
                {
                    val result = repository.getMealByArea(filterValue)
                    meal.postValue(ResultOf.Success(result))
                }
                else if (filterType=="Ingredients")
                {
                    val result = repository.getMealByIngredients(filterValue)
                    meal.postValue(ResultOf.Success(result))
                }

            }catch(ioe: IOException) {
                meal.postValue(ResultOf.Failure("[IO] error please retry", ioe))
            } catch (he: HttpException) {
                meal.postValue(ResultOf.Failure("[HTTP] error please retry", he))
            }
        }
    }
}