package com.example.mealapp.ui.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapp.data.model.categories.CategoriesModel
import com.example.mealapp.data.model.categories.CategoryModel
import com.example.mealapp.data.model.errorhandling.ResultOf
import com.example.mealapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val categories=MutableLiveData<ResultOf<CategoriesModel>>()

    fun getCategories(){
        viewModelScope.launch {
            try {
                val result = repository.getCategories()
               categories.postValue(ResultOf.Success(result))
            }catch(ioe: IOException) {
                categories.postValue(ResultOf.Failure("[IO] error please retry", ioe))
            } catch (he: HttpException) {
                categories.postValue(ResultOf.Failure("[HTTP] error please retry", he))
            }
        }
    }
}