package com.example.mealapp.ui.mealsdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapp.data.model.errorhandling.ResultOf
import com.example.mealapp.data.model.meals.details.MealDetailsModel
import com.example.mealapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val mealDetails= MutableLiveData<ResultOf<MealDetailsModel>>()

    fun getMealDetails(mealId: String){
        viewModelScope.launch {
            try {
                val result = repository.getMealDetails(mealId)
                mealDetails.postValue(ResultOf.Success(result))
            }catch(ioe: IOException) {
                mealDetails.postValue(ResultOf.Failure("[IO] error please retry", ioe))
            } catch (he: HttpException) {
                mealDetails.postValue(ResultOf.Failure("[HTTP] error please retry", he))
            }
        }
    }
}