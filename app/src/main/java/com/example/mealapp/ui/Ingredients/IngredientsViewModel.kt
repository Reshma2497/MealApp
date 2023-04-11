package com.example.mealapp.ui.Ingredients

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapp.data.model.Ingredients.IngredientsModel
import com.example.mealapp.data.model.errorhandling.ResultOf
import com.example.mealapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class IngredientsViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val ingredient=MutableLiveData<ResultOf<IngredientsModel>>()

    fun getIngredients(){
        viewModelScope.launch {
            try {
                val result = repository.getIngredients()
                ingredient.postValue(ResultOf.Success(result))
            }catch(ioe: IOException) {
                ingredient.postValue(ResultOf.Failure("[IO] error please retry", ioe))
            } catch (he: HttpException) {
                ingredient.postValue(ResultOf.Failure("[HTTP] error please retry", he))
            }
        }
    }
}

